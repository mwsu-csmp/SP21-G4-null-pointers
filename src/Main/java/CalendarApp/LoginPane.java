package CalendarApp;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
        Button add = new Button("Add");
        Button confirm = new Button("Confirm");
        Button clear = new Button("Clear");
        add.setStyle("-fx-background-color: #06f642; ");
        clear.setStyle("-fx-background-color: rgba(205,18,18,0.75); ");

        Label l = new Label("");
        EventHandler<ActionEvent> event = e -> {
            getChildren().addAll(new Label("Please enter your first and last name:"));
            TextField name = new TextField();
            name.setPrefColumnCount(10);
            getChildren().addAll(name);
            getChildren().addAll(new Label("Please enter your date of birth:"));
            DatePicker d = new DatePicker();
            getChildren().addAll(d, add, clear);

            final Label label = new Label();
            GridPane.setConstraints(label, 0, 3);
            GridPane.setColumnSpan(label, 2);
            getChildren().add(label);


            add.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e) {
                    String full_name;
                    full_name = name.getText();
                    LocalDate date1;
                    date1 = d.getValue();


                    if ((name.getText() != null && !name.getText().isEmpty())) {
                        User user = new User(full_name, date1);
                        clearWindow();
                        restart(postLoginAction);
                    }
                }
            });

            //Setting an action for the Clear button
            clear.setOnAction(event1 -> {
                name.setText("");
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
        add.setTranslateX(2);
        add.setTranslateY(-1);
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
        Button add = new Button("Add");
        Button clear = new Button("Clear");
        Button confirm = new Button("Confirm");
        add.setStyle("-fx-background-color: #06f642; ");
        clear.setStyle("-fx-background-color: rgba(205,18,18,0.75); ");

        Label l = new Label("");
        EventHandler<ActionEvent> event = e -> {
            getChildren().addAll(new Label("Please enter your first and last name:"));
            TextField name = new TextField();
            name.setPrefColumnCount(10);
            getChildren().addAll(name);
            getChildren().addAll(new Label("Please enter your date of birth:"));
            DatePicker d = new DatePicker();
            getChildren().addAll(d, add, clear);



            add.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e) {
                    String full_name;
                    full_name = name.getText();
                    LocalDate date1;
                    date1 = d.getValue();


                    if ((name.getText() != null && !name.getText().isEmpty())) {
                        User user = new User(full_name, date1);
                        clearWindow();
                        restart(postLoginAction);
                    }
                }
            });

            //Setting an action for the Clear button
            clear.setOnAction(event1 -> {
                name.setText("");
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
        add.setTranslateX(2);
        add.setTranslateY(-1);
        clear.setTranslateX(348);
        clear.setTranslateY(-65);
        y.setOnAction(event);
        n.setOnAction(event1);
        getChildren().addAll(y, n, l);

    }
}