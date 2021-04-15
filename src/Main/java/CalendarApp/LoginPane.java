package CalendarApp;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.naming.Name;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class LoginPane extends FlowPane  {
    // TODO: create a pane for user login, Josiah already has a good start at this!

    // TODO: possibly user could pick from list of created users, or create a new one. That way less typing is needed.

    static ArrayList<Name> names;

    static void nameDatabase() {
        names = new ArrayList<>();
        names.add((Name) new User("Josiah", LocalDate.now()));


    }

    public LoginPane(){
        setPadding(new Insets(20, 20, 200, 20));
        setHgap(5);
        setVgap(5);
        getChildren().addAll(new Label("Are you a first time user?"));

        Button y = new Button("Yes");
        Button n = new Button("No");
        Button a = new Button("Add");
        Button a1 = new Button("Add");

        Label l = new Label("");
        TextField b = new TextField("initial text");
        EventHandler<ActionEvent> event = e -> {
            getChildren().addAll(new Label("Please enter your first and last name:"));
            TextField tfMi = new TextField();
            tfMi.setPrefColumnCount(10);
            getChildren().addAll(tfMi, a);
            getChildren().addAll(new Label("Please enter your date of birth:"));
            DatePicker d = new DatePicker();
            getChildren().addAll(d, a1);

            a.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    Object full_name;
                    full_name = tfMi.getText();
                    names.add((Name) full_name);

                    //System.out.println(full_name);
                }
            });
            a1.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    Object date;
                    date = d.getValue();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
                    LocalDate date1 = LocalDate.parse((CharSequence) date, dateFormat);
                    //names.add((Name) date1);

                }

            });


        };

        l.setTranslateX(10);
        l.setTranslateY(-3);
        a.setTranslateX(2);
        a.setTranslateY(-1);
        y.setOnAction(event);
        getChildren().addAll(y, n, l);

    }
}
