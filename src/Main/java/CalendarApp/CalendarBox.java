package CalendarApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class CalendarBox extends StackPane {
    int day;
    int month;
    int year;

    public CalendarBox(int day, int month, int year, User user){
        this.day = day;
        this.month = month;
        this.year = year;

        Rectangle outline = new Rectangle();
        Label daynumber = new Label(day + ".");

        outline.setFill(null);
        outline.setStroke(Color.BLACK);
        outline.setWidth(100);
        outline.setHeight(100);

        getChildren().addAll(outline, daynumber);
        setAlignment(daynumber, Pos.TOP_LEFT);
        setMargin(daynumber, new Insets(4));
    }
    // TODO: Implement User parameter into CalendarBox for Special_Days
    // TODO: Add ability to pull calendar data from csv
    // TODO: Look into "Tooltips" for seeing Special_Day previews
}
