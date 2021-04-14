package Sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final int month = LocalDate.now().getMonth().getValue();
        final int year = LocalDate.now().getYear();

        CalendarPane calendarPane = new CalendarPane(month, year);
        Scene scene = new Scene(calendarPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}


