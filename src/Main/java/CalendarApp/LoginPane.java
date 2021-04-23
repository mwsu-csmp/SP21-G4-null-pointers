package CalendarApp;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;


public class LoginPane extends FlowPane  {
    // TODO: create a pane for user login, Josiah already has a good start at this!

    // TODO: possibly user could pick from list of created users, or create a new one. That way less typing is needed.

    public void clearWindow() {
        getChildren().clear();
    }

    public void restart(Consumer<User> postLoginAction) {
        setPadding(new Insets(100, 150, 150, 200));
        setHgap(5);
        setVgap(5);
        getChildren().addAll(new Label("Are you a first time user?"));

        Button y = new Button("Yes");
        Button n = new Button("No");
        Button a = new Button("Add");
        Button confirm = new Button("Confirm");
        Button clear = new Button("Clear");
        a.setStyle("-fx-background-color: #06f642; ");
        clear.setStyle("-fx-background-color: rgba(205,18,18,0.75); ");

        Label l = new Label("");
        EventHandler<ActionEvent> event = e -> {
            getChildren().addAll(new Label("Please enter your first and last name:"));
            TextField tfMi = new TextField();
            tfMi.setPrefColumnCount(10);
            getChildren().addAll(tfMi);
            getChildren().addAll(new Label("Please enter your date of birth:"));
            DatePicker d = new DatePicker();
            getChildren().addAll(d, a, clear);

            final Label label = new Label();
            GridPane.setConstraints(label, 0, 3);
            GridPane.setColumnSpan(label, 2);
            getChildren().add(label);


            a.setOnAction(event1 -> {
                    if ((tfMi.getText() != null && !tfMi.getText().isEmpty())) {
                        clearWindow();
                        restart(postLoginAction);
                    }
                });

            //Setting an action for the Clear button
            clear.setOnAction(event1 -> {
                    tfMi.setText("");
                    d.getEditor().clear();
                });
        };
        EventHandler<ActionEvent> event1 = e -> {
            Label z = new Label("This is a choice box");
            ChoiceBox<String> c = new ChoiceBox<>();
            File folder = new File("./resources/Users");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                c.getItems().add(file.getName());
            }
            confirm.setOnAction(confirmUser -> {
                try {
                    postLoginAction.accept(new User(c.getValue()));
                } catch (IOException o) {
                    // TODO react to Special_Days file not being found
                    System.out.println("could not find Special_Days file!");
                }
            });
            getChildren().add(confirm);
            getChildren().add(z);
            getChildren().add(c);
            z.setTranslateX(-280);           //TODO Fix add a GridPane
            z.setTranslateY(30);
            c.setTranslateX(115);
            c.setTranslateY(0);
            confirm.setTranslateX(10);
            confirm.setTranslateY(30);

        };


        l.setTranslateX(10);
        l.setTranslateY(-3);
        a.setTranslateX(2);
        a.setTranslateY(-1);
        clear.setTranslateX(348);
        clear.setTranslateY(-65);
        y.setOnAction(event);
        n.setOnAction(event1);
        getChildren().addAll(y, n, l);

    }

    // TODO: Create more meaningful variable names
    public LoginPane(Consumer<User> postLoginAction) {
        setPadding(new Insets(100, 150, 150, 200));
        setHgap(5);
        setVgap(5);
        getChildren().addAll(new Label("Are you a first time user?"));


        Button y = new Button("Yes");
        Button n = new Button("No");
        Button a = new Button("Add");
        Button clear = new Button("Clear");
        Button confirm = new Button("Confirm");
        a.setStyle("-fx-background-color: #06f642; ");
        clear.setStyle("-fx-background-color: rgba(205,18,18,0.75); ");

        Label l = new Label("");
        EventHandler<ActionEvent> event = e -> {
            getChildren().addAll(new Label("Please enter your first and last name:"));
            TextField tfMi = new TextField();
            tfMi.setPrefColumnCount(10);
            getChildren().addAll(tfMi);
            getChildren().addAll(new Label("Please enter your date of birth:"));
            DatePicker d = new DatePicker();
            getChildren().addAll(d, a, clear);



            a.setOnAction(event1 -> {
                    if ((tfMi.getText() != null && !tfMi.getText().isEmpty())) {
                        clearWindow();
                        restart(postLoginAction);
                    }
            });

            //Setting an action for the Clear button
            clear.setOnAction(event1 -> {
                    tfMi.setText("");
                    d.getEditor().clear();
            });
        };

        EventHandler<ActionEvent> event1 = e -> {
            Label z = new Label("This is a choice box");
            ChoiceBox<String> c = new ChoiceBox<>();
            File folder = new File("./resources/Users");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                c.getItems().add(file.getName());
            }
            confirm.setOnAction(confirmUser -> {
                try {
                    postLoginAction.accept(new User(c.getValue()));
                } catch (IOException o) {
                    // TODO react to Special_Days file not being found
                    System.out.println("could not find Special_Days file!");
                }
            });
            getChildren().add(confirm);
            getChildren().add(z);
            getChildren().add(c);
            z.setTranslateX(-280);           //TODO Fix add a GridPane
            z.setTranslateY(30);
            c.setTranslateX(115);
            c.setTranslateY(0);
            confirm.setTranslateX(30);
            confirm.setTranslateY(30);

        };




        l.setTranslateX(10);
        l.setTranslateY(-3);
        a.setTranslateX(2);
        a.setTranslateY(-1);
        clear.setTranslateX(348);
        clear.setTranslateY(-65);
        y.setOnAction(event);
        n.setOnAction(event1);
        getChildren().addAll(y, n, l);

    }
}