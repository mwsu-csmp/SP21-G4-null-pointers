package sample;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String name;
    private final LocalDate birthday;
    private final ArrayList<Special_Day> special_days;

    public User(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
        special_days = new ArrayList<>();
    }

    public  String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

}
