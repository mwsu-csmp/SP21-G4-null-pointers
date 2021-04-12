package Sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        final int month = 2;
        final int year = 2021;

        Calendar CalendarBody = new Calendar(month, year);
        BorderPane borderPane = new BorderPane(CalendarBody);
        CalendarBody.populateCalendar(month, year);

        Text toptext = new Text(CalendarBody.dateToString());
        Button nextmonth = new Button(">");
        nextmonth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CalendarBody.moveMonthForwards();
                toptext.setText(CalendarBody.dateToString());
            }
        });
        Button previousmonth = new Button("<");
        previousmonth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CalendarBody.moveMonthBackwards();
                toptext.setText(CalendarBody.dateToString());
            }
        });
        HBox top = new HBox(previousmonth, nextmonth, toptext);
        borderPane.setTop(top);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}


