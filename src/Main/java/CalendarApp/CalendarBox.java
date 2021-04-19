package CalendarApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class CalendarBox extends StackPane {

    public CalendarBox(int day, int month, int year, User user){

        LocalDate currentday = LocalDate.of(year, month, day);

        // Outline and number of box
        Rectangle outline = new Rectangle();
        Label daynumber = new Label(day + ".");
        outline.setFill(null);
        outline.setStroke(Color.BLACK);
        outline.setWidth(150);
        outline.setHeight(150);

        // List of events in box
        VBox events = new VBox();
        events.alignmentProperty().setValue(Pos.TOP_RIGHT);
        events.setAlignment(Pos.BASELINE_RIGHT);
        events.setMaxWidth(110);

        // Adds and aligns children
        getChildren().addAll(outline, daynumber, events);
        setAlignment(daynumber, Pos.TOP_LEFT);
        setMargin(daynumber, new Insets(4));
        setMargin(events, new Insets(4));

        // Dropdown for event overflow
        ComboBox<String> eventsdropdown = new ComboBox<>();

        // Adds each Special_Day to events if they meet criteria
        int extraevents = 0;
        for (Special_Day special_day : user.getSpecial_Days()) {
            if (events.getChildren().size() < 4){
                if (currentday.isAfter(special_day.getStartdate()) && currentday.isBefore(special_day.getEnddate()))
                    events.getChildren().add(new EventButton(user, special_day));
                else if (currentday.isEqual(special_day.getStartdate()) || currentday.isEqual(special_day.getEnddate()))
                    events.getChildren().add(new EventButton(user, special_day));
            }
            // if event overflow, add to dropdown instead
            else {
                if (events.getChildren().size() == 4) events.getChildren().add(eventsdropdown);
                if (currentday.isAfter(special_day.getStartdate()) && currentday.isBefore(special_day.getEnddate())) {
                    eventsdropdown.getItems().add((special_day.getTitle()));
                    extraevents++;
                }
                else if (currentday.isEqual(special_day.getStartdate()) || currentday.isEqual(special_day.getEnddate())) {
                    eventsdropdown.getItems().add((special_day.getTitle()));
                    extraevents++;
                }
            }


        }
    }
    // TODO: Add ability to pull calendar data from csv
    // TODO: Look into "Tooltips" for seeing Special_Day previews
}
