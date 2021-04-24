package CalendarApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.function.Consumer;


public class Countdown extends GridPane {
    private final Calendar calendar;

    public Countdown(User user, Consumer<Boolean> postAddEventAction) {
        calendar = user.getCalendar();
    }


    public Countdown(User user, Consumer<Boolean> postAddEventAction, Special_Day special_day){
        calendar = user.getCalendar();
        setAlignment(Pos.BASELINE_RIGHT);
        setHgap(50);
        setVgap(40);
        setPadding(new Insets(25));
        generateCountDown(user,special_day);
    }




    private void generateCountDown(User user, Special_Day special_day) {
        System.out.println(special_day.getStartdate());

    }
}
