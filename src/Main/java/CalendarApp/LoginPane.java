package CalendarApp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoginPane extends FlowPane {
    // TODO: create a pane for user login, Josiah already has a good start at this!

    // TODO: possibly user could pick from list of created users, or create a new one. That way less typing is needed.


    public LoginPane(){
        setPadding(new Insets(20, 20, 200, 20));
        setHgap(5);
        setVgap(5);
        getChildren().addAll(new Label("Are you a first time user?"));

        Button y = new Button("Yes");
        Button n = new Button("No");
        Label l = new Label("");
        EventHandler<ActionEvent> event = e -> {
            l.setText("   Please enter your name:   ");
            getChildren().addAll(new Label("First Name:"), new TextField(), new Label("MI"));
            TextField tfMi = new TextField();
            tfMi.setPrefColumnCount(1);
            getChildren().addAll(tfMi, new Label("Last Name:"), new TextField());

        };
        l.setTranslateX(10);
        l.setTranslateY(-3);
        y.setOnAction(event);
        getChildren().addAll(y, n, l);

    }
}


   /* public void start(Stage primaryStage) {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.getChildren().addAll(new Label("Are you a first time user?"));
        primaryStage.setTitle("creating buttons");
        Button y = new Button("Yes");
        Button n = new Button("No");
        StackPane r = new StackPane();
        y.setTranslateX(30);
        y.setTranslateY(-102);
        n.setTranslateX(70);
        n.setTranslateY(-102);
        Label l = new Label("Please enter your first name:");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                l.setText("   button   selected    ");

            }
        };
        r.getChildren().add(y);
        r.getChildren().add(n);
        r.getChildren().add(pane);
        y.setOnAction(event);
        r.getChildren().add(l);
        primaryStage.setTitle("Calender");
        primaryStage.setScene(new Scene(r, 300, 250));
        primaryStage.show();

    }
} */