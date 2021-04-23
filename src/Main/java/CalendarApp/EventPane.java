package CalendarApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class EventPane extends GridPane {
    private final TextField eventTitle = new TextField();
    private final DatePicker startDate = new DatePicker();
    private final DatePicker endDate = new DatePicker();
    private final TextField startTime = new TextField(LocalTime.now().plusHours(1).format(DateTimeFormatter.ofPattern("hh:mm a")));
    private final TextField endTime = new TextField(LocalTime.now().plusHours(2).format(DateTimeFormatter.ofPattern("hh:mm a")));
    private final CheckBox isAllDay = new CheckBox();
    private final TextField eventDescription = new TextField();
    private final TextField eventLocation = new TextField();
    private final CheckBox isPrivate = new CheckBox();
    private final Label errorMessage = new Label("");
    private final HBox times = new HBox();
    private final Calendar calendar;

    /** Creates an EventPane for making a new Special_Day
     *
     * @param user current user logged in
     * @param postAddEventAction action to be done after event is added
     */
    public EventPane(User user, Consumer<Boolean> postAddEventAction){

        calendar = user.getCalendar();

        Text scenetitle = new Text("Add Event");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitle, 0, 0, 2, 1);

        Button addButton = new Button("Add");
        add(addButton, 1, 8);

        addButton.setOnAction(event -> {
                    try {
                        user.addSpecialDay(generateSpecial_Day());
                        calendar.populateCalendar();
                        postAddEventAction.accept(true);
                    } catch (IllegalArgumentException e) {
                        errorMessage.setText("ERROR: " + e.getMessage());
                    } catch (DateTimeParseException e) {
                        errorMessage.setText("ERROR: Date format is incorrect, try hh:mm AM/PM format");
                    }
                });
        populateEventPane();
    }

    /** Constructs a window to view or edit a selected event
     *
     * @param user current user logged in
     * @param postAddEventAction action to be done after event is added
     * @param special_day special_day being edited
     */
    public EventPane(User user, Consumer<Boolean> postAddEventAction, Special_Day special_day) {

        calendar = user.getCalendar();

        Text scenetitle = new Text("View Event");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitle, 0, 0, 2, 1);

        populateEventPane();
        populateEventPaneParameters(special_day);

        ArrayList<Node> nodes = new ArrayList<>(
                Arrays.asList(eventTitle,
                            startDate,
                            endDate,
                            startTime,
                            endTime,
                            isAllDay,
                            isPrivate,
                            eventDescription,
                            eventLocation));

        for (Node node : nodes) {
            node.setDisable(true);
        }

        Button edit = new Button("Edit");
        Button cancel = new Button("Cancel");
        cancel.setVisible(false);
        Button confirm = new Button("Confirm");
        confirm.setVisible(false);

        HBox choices = new HBox();
        choices.getChildren().addAll(edit, cancel, confirm);
        add(choices, 0, 8, 2, 1);

        edit.setOnAction(event ->{
            edit.setVisible(false);
            for (Node node : nodes)
                node.setDisable(false);
            cancel.setVisible(true);
            confirm.setVisible(true);
            scenetitle.setText("Edit Event");
        });

        cancel.setOnAction(event -> {
            cancel.setVisible(false);
            confirm.setVisible(false);
            for (Node node : nodes)
                node.setDisable(true);
            edit.setVisible(true);
            populateEventPaneParameters(special_day);
            scenetitle.setText("View Event");
        });

        confirm.setOnAction(event -> {
            try {
                user.addSpecialDay(generateSpecial_Day());
                user.removeSpecialDay(special_day);
                calendar.populateCalendar();
                postAddEventAction.accept(true);
            } catch (IllegalArgumentException e) {
                errorMessage.setText("ERROR: " + e.getMessage());
            } catch (DateTimeParseException e) {
                errorMessage.setText("ERROR: Date format is incorrect, try hh:mm AM/PM format");
            }
            // TODO: Add delete button for existing events
        });

    }

    private void populateEventPane() {

        Text scenetitle = new Text("");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitle, 0, 0, 2, 1);

        setAlignment(Pos.BASELINE_RIGHT);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25));

        Label eventTitleLabel = new Label("Title:");
        add(eventTitleLabel, 0, 1);
        add(eventTitle, 1, 1);

        Label divider1 = new Label("-");
        HBox dates = new HBox(startDate, divider1, endDate);
        add(dates, 0, 2, 2, 1);

        Label divider2 = new Label("-");
        times.getChildren().addAll(startTime, divider2, endTime);
        add(times, 0, 3, 2, 1);

        Label isAllDayLabel = new Label("All Day");
        add(isAllDayLabel, 0, 4);
        add(isAllDay, 1, 4);

        Label eventDescriptionLabel = new Label("Description:");
        add(eventDescriptionLabel, 0, 5);
        add(eventDescription, 1, 5);

        Label eventLocationLabel = new Label("Location:");
        add(eventLocationLabel, 0, 6);
        add(eventLocation, 1, 6);

        Label isPrivateLabel = new Label("Private");
        add(isPrivateLabel, 0, 7);
        add(isPrivate, 1, 7);

        add(errorMessage, 0, 9, 2, 1);

        isAllDay.setOnAction(event -> times.setVisible(!isAllDay.isSelected()));
    }

    private void populateEventPaneParameters(Special_Day special_day) {

        eventTitle.setText(special_day.getTitle());
        startDate.setValue(special_day.getStartdate());
        endDate.setValue(special_day.getEnddate());
        if (!special_day.isAllDay()) {
            startTime.setText(special_day.getStarttime().format(DateTimeFormatter.ofPattern("hh:mm a")));
            endTime.setText(special_day.getEndtime().format(DateTimeFormatter.ofPattern("hh:mm a")));
        } else {
            times.setVisible(false);
        }
        isAllDay.setSelected(special_day.isAllDay());
        isPrivate.setSelected(special_day.isPrivate());
        eventDescription.setText(special_day.getDescription());
        eventLocation.setText(special_day.getLocation());

    }

    private Special_Day generateSpecial_Day() {
        if (isAllDay.isSelected()) {
            return new Special_Day(eventTitle.getText(), startDate.getValue(), null, endDate.getValue(), null, isAllDay.isSelected(), eventDescription.getText(), eventLocation.getText(), isPrivate.isSelected());
        }
        else {
            return new Special_Day(eventTitle.getText(), startDate.getValue(), LocalTime.parse(startTime.getText(), DateTimeFormatter.ofPattern("hh:mm a")), endDate.getValue(), LocalTime.parse(endTime.getText(), DateTimeFormatter.ofPattern("hh:mm a")), isAllDay.isSelected(), eventDescription.getText(), eventLocation.getText(), isPrivate.isSelected());
        }
    }
}
