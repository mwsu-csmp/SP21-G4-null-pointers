package CalendarApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;


public class Countdown extends GridPane {
    private final Calendar calendar;

    public Countdown(User user, Consumer<User> postLoginAction) {
        calendar = user.getCalendar();
        setPadding(new Insets(200, 200, 200, 200));
        setHgap(5);
        setVgap(5);
        ChoiceBox<String> choice = new ChoiceBox<>();
        for (Special_Day special_day : user.getSpecial_Days()) {
            System.out.println(special_day.getTitle());
            choice.getItems().add(special_day.getTitle());
        }
        try {
            postLoginAction.accept(new User(choice.getValue()));
        } catch (IOException o) {
            final Label label = new Label("Please select a name from the box!");
        }
        getChildren().add(choice);

    }
}

