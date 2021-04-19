package CalendarApp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class CalendarPane extends BorderPane {
    private final Calendar CalendarBody;
    private final Label datetext;
    private final Button nextmonth;
    private final Button previousmonth;
    private final Button addevent;


    /** Pane that contains a calendar as well as some buttons
     *
     * @param month Current month being displayed
     * @param year Current year being displayed
     * @param user Current user logged in
     */
    public CalendarPane(int month, int year, User user){
        CalendarBody = new Calendar(month, year, user);
        datetext = new Label(CalendarBody.dateToString());
        nextmonth = new Button(">");
        previousmonth = new Button("<");
        addevent = new Button("Add Event");
        user.setCalendar(CalendarBody);

        HBox topSector = new HBox(previousmonth, datetext, nextmonth);
        HBox bottomSector = new HBox(addevent);

        nextmonth.setOnAction(event -> {
            CalendarBody.moveMonthForwards();
            datetext.setText(CalendarBody.dateToString());
        });

        previousmonth.setOnAction(event -> {
            CalendarBody.moveMonthBackwards();
            datetext.setText(CalendarBody.dateToString());
        });

        addevent.setOnAction(event -> {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(new EventPane(user, stage));
            stage.setAlwaysOnTop(true);
            stage.setResizable(false);
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
