package CalendarApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private final String name;
    private final ArrayList<Special_Day> special_days;
    private Calendar calendar;

    /** creates a new user with a given birthday
     *
     * @param name name of the user
     * @param birthday the user's birthday
     * @throws IllegalArgumentException if birthday is null
     */
    public User(String name, LocalDate birthday) throws IllegalArgumentException{
        if (birthday == null) throw new IllegalArgumentException("Bad birthday!");
        this.name = name;
        special_days = new ArrayList<>();
        special_days.add(new Special_Day("My birthday!", birthday, null, birthday, null, true, "It's my birthday!", null, true, true));
        makeUserDirectory();
        try {
            saveSpecial_Days();
        } catch (IOException e) {
            //TODO: add exception handle
        }

    }

    /** re-creates an existing user
     *
     * @param name name of the user
     * @throws IOException if Special_Days file cannot be found
     */
    public User(String name) throws IOException {
        this.name = name;
        special_days = new ArrayList<>();
        loadSpecial_Days();
    }

    public void addSpecialDay(Special_Day special_day){
        special_days.add(special_day);
    }

    public void removeSpecialDay(Special_Day special_day){
        special_days.remove(special_day);
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    public Calendar getCalendar() {
        return calendar;
    }

    public ArrayList<Special_Day> getSpecial_Days(){
        return special_days;
    }

    public void loadSpecial_Days() throws IOException {
        File saveFile = new File("./resources/Users/" + name + "/specialdays.txt");
        Scanner in = new Scanner(saveFile);
        while(in.hasNext()){
            special_days.add(new Special_Day(in.nextLine()));
        }
        in.close();
    }

    public void saveSpecial_Days() throws IOException {
        File saveFile = new File("./resources/Users/" + name + "/specialdays.txt");
        var out = new FileWriter(saveFile);
        for (Special_Day special_day : special_days) {
            out.write(special_day.serialize() + "\n");
        }
        out.close();
    }

    private void makeUserDirectory(){
        File directory = new File("./resources/Users/" + name);
        if (!directory.mkdir()) {
            System.out.println("Could not make directory");
        }
    }

    // TODO: Add ability to Serialize special day into a string for output
}