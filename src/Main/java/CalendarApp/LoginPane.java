package CalendarApp;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.ArrayList;


public class LoginPane extends FlowPane  {
    // TODO: create a pane for user login, Josiah already has a good start at this!

    // TODO: possibly user could pick from list of created users, or create a new one. That way less typing is needed.

    static ArrayList<User> names;

    static void nameDatabase() {
        names = new ArrayList<>();
        names.add(new User("Josiah", LocalDate.now()));


    }
    public void clearWindow() {
        getChildren().clear();
    }

    public void restart() {

        /**
         public void clearWindow() {
         getchildren().clear();
         }
         */
        setPadding(new Insets(20, 20, 200, 20));
        setHgap(5);
        setVgap(5);
        getChildren().addAll(new Label("Are you a first time user?"));

        Button y = new Button("Yes");
        Button n = new Button("No");
        Button a = new Button("Add");
        Button clear = new Button("Clear");

        Label l = new Label("");
        TextField b = new TextField("initial text");
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


            a.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e) {
                    String full_name;
                    full_name = tfMi.getText();
                    LocalDate date1;
                    date1 = d.getValue();


                    if ((tfMi.getText() != null && !tfMi.getText().isEmpty())) {
                        User user = new User(full_name, date1);

                        nameDatabase();
                        names.clear();

                        while (true) {
                            //user.setName(full_name);
                            //user.setBirthday(date1);
                            names.add(user);
                            break;
                        }

                        for(int i = 0; i < names.size(); i++) {
                            names.get(i).setName(full_name);
                            names.get(i).setBirthday(date1);
                            System.out.println(names.get(i).getName() + " " + names.get(i).getBirthday());
                            break;
                        }
                        clearWindow();
                        restart();
                    }
                }

            });

            //Setting an action for the Clear button
            clear.setOnAction(new EventHandler<ActionEvent>() {

                // @Override
                public void handle(ActionEvent e) {
                    tfMi.setText("");
                    d.getEditor().clear();
                }
            });

        };


        l.setTranslateX(10);
        l.setTranslateY(-3);
        a.setTranslateX(2);
        a.setTranslateY(-1);
        clear.setTranslateX(348);
        clear.setTranslateY(-65);
        y.setOnAction(event);
        getChildren().addAll(y, n, l);

    }

    // TODO: Create more meaningful variable names
    public LoginPane(){



        /**
        public void clearWindow() {
            getchildren().clear();
        }
         */
        setPadding(new Insets(20, 20, 200, 20));
        setHgap(5);
        setVgap(5);
        getChildren().addAll(new Label("Are you a first time user?"));

        Button y = new Button("Yes");
        Button n = new Button("No");
        Button a = new Button("Add");
        Button clear = new Button("Clear");

        Label l = new Label("");
        TextField b = new TextField("initial text");
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


            a.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e) {
                    String full_name;
                    full_name = tfMi.getText();
                    LocalDate date1;
                    date1 = d.getValue();


                    if ((tfMi.getText() != null && !tfMi.getText().isEmpty())) {
                        User user = new User(full_name, date1);

                        nameDatabase();
                        names.clear();

                        while (true) {
                            //user.setName(full_name);
                            //user.setBirthday(date1);
                            names.add(user);
                            break;
                        }

                        for(int i = 0; i < names.size(); i++) {
                            names.get(i).setName(full_name);
                            names.get(i).setBirthday(date1);
                            System.out.println(names.get(i).getName() + " " + names.get(i).getBirthday());
                            break;
                        }
                        clearWindow();
                        restart();
                    }
                }

            });

            //Setting an action for the Clear button
            clear.setOnAction(new EventHandler<ActionEvent>() {

                // @Override
                public void handle(ActionEvent e) {
                    tfMi.setText("");
                    d.getEditor().clear();
                }
            });

        };


        l.setTranslateX(10);
        l.setTranslateY(-3);
        a.setTranslateX(2);
        a.setTranslateY(-1);
        clear.setTranslateX(348);
        clear.setTranslateY(-65);
        y.setOnAction(event);
        getChildren().addAll(y, n, l);

    }
}