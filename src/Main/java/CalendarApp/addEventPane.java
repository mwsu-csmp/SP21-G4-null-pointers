package CalendarApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class addEventPane extends GridPane {
    private final TextField eventTitleField;
    private final DatePicker startDate;
    private final DatePicker endDate;
    private final TextField startTime;
    private final TextField endTime;
    private final CheckBox isAllDay;
    private final CheckBox isPrivate;
    private final Button addButton;

    public addEventPane(User user){

        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add Event");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitle, 0, 0, 2, 1);

        Label eventTitle = new Label("Title:");
        add(eventTitle, 0, 1);
        eventTitleField = new TextField();
        add(eventTitleField, 1, 1);

        startDate = new DatePicker(LocalDate.now());
        endDate = new DatePicker(LocalDate.now());
        Label divider = new Label(" - ");
        HBox dates = new HBox(startDate, divider, endDate);
        add(dates, 0, 2, 2, 1);

        startTime = new TextField(LocalTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("hh:mm a")));
        endTime = new TextField(LocalTime.now().plusHours(2).format(DateTimeFormatter.ofPattern("hh:mm a")));
        HBox times = new HBox(startTime, divider, endTime);
        add(times, 0, 3, 2, 1);

        Label isAllDayLabel = new Label("All Day");
        add(isAllDayLabel, 0, 4);
        isAllDay = new CheckBox();
        add(isAllDay, 1, 4);

        Label isPrivateLabel = new Label("Private:");
        add(isPrivateLabel, 0, 5);
        isPrivate = new CheckBox();
        add(isPrivate, 1, 5);

        addButton = new Button("Add");
        add(addButton, 1, 6);

    }
}
