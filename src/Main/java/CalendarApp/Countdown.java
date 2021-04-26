package CalendarApp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.converter.LocalDateStringConverter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public class Countdown extends GridPane {
    private final Calendar calendar;

    public Countdown(User user, Consumer<User> postLoginAction) {
        calendar = user.getCalendar();
        setPadding(new Insets(200, 200, 200, 200));
        setHgap(5);
        setVgap(5);
        Label select = new Label("Please Select Which Date To Countdown:");
        Button calculate = new Button("Calculate");
        ChoiceBox<String> choice = new ChoiceBox<>();
        for (Special_Day special_day : user.getSpecial_Days()) {
            System.out.println(special_day.getTitle());
            choice.getItems().add(special_day.getTitle());
        }
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                for (Special_Day special_day : user.getSpecial_Days()) {
                    LocalDate now = LocalDate.now();
                    LocalTime date1 = special_day.getStarttime();
                    /**
                    Period diff = Period.between(now, LocalDate.from(date1));

                    System.out.printf("Difference is %d years, %d months and %d days old",
                            diff.getYears(), diff.getMonths(), diff.getDays());
*/
                }
            }
        });
        try {
            postLoginAction.accept(new User(choice.getValue()));
        } catch (IOException o) {
            final Label label = new Label("Please select a name from the box!");
        }
        getChildren().add(select);
        getChildren().add(choice);
        getChildren().add(calculate);
        calculate.setTranslateX(150);
        calculate.setTranslateY(-10);
        select.setTranslateX(0);
        select.setTranslateY(-40);
        choice.setTranslateX(20);
        choice.setTranslateY(-10);

    }
}

