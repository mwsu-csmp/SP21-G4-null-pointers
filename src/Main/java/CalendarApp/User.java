package CalendarApp;

import java.time.LocalDate;
import java.util.ArrayList;

public class User extends LoginPane{
    private String name;
    private LocalDate birthday;
    private final ArrayList<Special_Day> special_days;


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

    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birhtday) {
        this.birthday = birhtday;
    }

    // TODO: Add ability to Serialize special day into a string for output
}
