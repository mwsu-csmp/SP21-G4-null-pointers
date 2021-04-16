package CalendarApp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Special_Day {
    private String title;
    private LocalDate startdate;
    private LocalDate enddate;
    private LocalTime starttime;
    private LocalTime endtime;
    private boolean isallday;
    private String description;
    private String location;
    private boolean isprivate;

    public Special_Day(String title, LocalDate startdate, LocalTime starttime, LocalDate enddate, LocalTime endtime, boolean isallday, String description, String location, boolean isprivate) {
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        if (isallday){
            this.starttime = starttime;
            this.endtime = endtime;
        } else {
            this.starttime = null;
            this.endtime = null;
        }
        this.isallday = isallday;
        this.description = description;
        this.location = location;
        this.isprivate = isprivate;

    }

    public void update(String title, LocalDate startdate, LocalTime starttime, LocalDate enddate, LocalTime endtime, boolean isallday, String description, String location, boolean isprivate) {
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        if (!isallday){
            this.starttime = starttime;
            this.endtime = endtime;
        } else {
            this.starttime = null;
            this.endtime = null;
        }
        this.isallday = isallday;
        this.description = description;
        this.location = location;
        this.isprivate = isprivate;
    }

    public String serialize() {
        ArrayList<String> returnlist = new ArrayList<>();
        returnlist.add(title);
        returnlist.add(startdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if (!isallday)
            returnlist.add(starttime.format(DateTimeFormatter.ofPattern("hh:mm a")));
        else returnlist.add("");
        returnlist.add(enddate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if (!isallday)
            returnlist.add(endtime.format(DateTimeFormatter.ofPattern("hh:mm a")));
        else returnlist.add("");
        if (isallday) returnlist.add("TRUE");
        else returnlist.add("FALSE");
        returnlist.add(description);
        returnlist.add(location);
        if (isprivate) returnlist.add("TRUE");
        else returnlist.add("FALSE");

        return "\"" + returnlist.get(0) + "\", \"" +
                returnlist.get(1) + "\", \"" +
                returnlist.get(2) + "\", \"" +
                returnlist.get(3) + "\", \"" +
                returnlist.get(4) + "\", \"" +
                returnlist.get(5) + "\", \"" +
                returnlist.get(6) + "\", \"" +
                returnlist.get(7) + "\", \"" +
                returnlist.get(8) + "\"";
    }

    // TODO: Create an overloaded constructor that deserializes a String into a Special_Day object


}
