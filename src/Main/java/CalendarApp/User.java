package CalendarApp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String name;
    private LocalDate birthday;
    private final ArrayList<Special_Day> special_days;
    private Calendar calendar;


    public User(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
        special_days = new ArrayList<>();
        special_days.add(new Special_Day("My birthday!", birthday, null, birthday, null, true, "It's my birthday!", null, true));
    }

    public void addSpecialDay(Special_Day special_day){
        special_days.add(special_day);
    }

    public void removeSpecialDay(Special_Day special_day){
        special_days.remove(special_day);
    }

    public String getName() {
        return name;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    public Calendar getCalendar() {
        return calendar;
    }

    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public ArrayList<Special_Day> getSpecial_Days(){
        return special_days;
    }

        // TODO: Add ability to Serialize special day into a string for output
}
