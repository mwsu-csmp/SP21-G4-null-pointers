package CalendarApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;


public class Countdown extends GridPane {
    private final Calendar calendar;

    public Countdown(User user, Consumer<User> postLoginAction) {

        setPadding(new Insets(50));
        Label select = new Label("Please Select Which Dates To Countdown:");
        Label display = new Label();

        HBox hbox = new HBox();
        hbox.setSpacing(25);

        Button calculate = new Button("Calculate");
        Button clear = new Button("Clear");
        ChoiceBox<Special_Day> choice = new ChoiceBox<>();
        ChoiceBox<Special_Day> choice2 = new ChoiceBox<>();

        for (Special_Day special_day : user.getSpecial_Days()) {
            System.out.println(special_day.getTitle());
            choice.getItems().add(special_day);
        }
        try {
            choice.getItems().add(new Special_Day("Today", LocalDate.now(), LocalTime.now(), LocalDate.now(), LocalTime.now(), true, null, null, false, false));
        } catch (IllegalArgumentException e) {
            System.out.println("You shouldn't be seeing this"); //TODO: Redo this later
        }

        for (Special_Day special_day : user.getSpecial_Days()) {
            System.out.println(special_day.getTitle());
            choice2.getItems().add(special_day);
        }

        calculate.setOnAction(event -> {
            Long diff = choice.getValue().getStartdate().until(choice2.getValue().getStartdate(), ChronoUnit.DAYS);
            display.setText("There are " + diff + " days between " + choice.getValue() + " and " + choice2.getValue());
            System.out.println(diff);
        });

        clear.setOnAction(event1 -> {
            choice.getSelectionModel().clearSelection();
            choice2.getSelectionModel().clearSelection();
            choice.setValue(null);
            choice2.setValue(null);
            display.setText("");


        });

        hbox.getChildren().addAll(select, choice, choice2, calculate, clear);
        getChildren().addAll(hbox, display);
        hbox.setAlignment(Pos.CENTER);
        display.setAlignment(Pos.BOTTOM_CENTER);
        display.setTranslateY(50);



    }
}
