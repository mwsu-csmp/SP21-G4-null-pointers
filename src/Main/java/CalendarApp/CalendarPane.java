package CalendarApp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class CalendarPane extends BorderPane {
    private final Calendar CalendarBody;
    private final Label datetext;
    private final Button nextmonth;
    private final Button previousmonth;
    private final Button addevent;

    public CalendarPane(int month, int year, User user){
        CalendarBody = new Calendar(month, year, user);
        datetext = new Label(CalendarBody.dateToString());
        nextmonth = new Button(">");
        previousmonth = new Button("<");
        addevent = new Button("Add Event");

        HBox topSector = new HBox(previousmonth, datetext, nextmonth);
        HBox bottomSector = new HBox(addevent);

        // TODO: Implement User parameter into CalendarPane for Special_Days

        nextmonth.setOnAction(event -> {
            CalendarBody.moveMonthForwards();
            datetext.setText(CalendarBody.dateToString());
        });

        previousmonth.setOnAction(event -> {
            CalendarBody.moveMonthBackwards();
            datetext.setText(CalendarBody.dateToString());
        });

        addevent.setOnAction(event -> {
            Scene scene = new Scene(new addEventPane(user));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        previousmonth.setTooltip(new Tooltip("Goes back a month"));
        nextmonth.setTooltip(new Tooltip("Goes forward a month"));

        previousmonth.alignmentProperty().setValue(Pos.CENTER_LEFT);
        datetext.alignmentProperty().setValue(Pos.CENTER);
        nextmonth.alignmentProperty().setValue(Pos.CENTER_RIGHT);

        topSector.setAlignment(Pos.CENTER);
        topSector.setSpacing(100);
        CalendarBody.setAlignment(Pos.TOP_CENTER);

        setCenter(CalendarBody);
        setTop(topSector);
        setBottom(bottomSector);
    }
}
