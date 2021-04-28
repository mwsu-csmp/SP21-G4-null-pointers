package CalendarApp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class EventButton extends Button {

    public EventButton(User user ,Special_Day special_day) {
        super(special_day.getTitle());

        if (!special_day.getDescription().equals(""))
            setTooltip(new Tooltip(special_day.getDescription()));

        setOnAction(actionEvent -> {
            Stage stage = new Stage();
            Scene scene = new Scene(new EventPane(user, closestage -> stage.close(), special_day));
            stage.setAlwaysOnTop(true);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        });
    }
}