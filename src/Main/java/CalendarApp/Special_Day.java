package CalendarApp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

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

    public Special_Day(String csvDesc) throws IllegalArgumentException{
        try {
            var fields = Arrays.asList(csvDesc.split(","));
            var newfields = new ArrayList<String>();
            if (fields.size() != 9) throw new IllegalArgumentException("Improper string length");

            for (String field : fields) {
                if ((field.charAt(0) != '\"' || field.charAt(field.length()-1) != '\"'))
                    throw new IllegalArgumentException();
                else newfields.add(field.strip().substring(1, field.length() - 1));
            }

            if (!newfields.get(5).equals("TRUE") && !newfields.get(5).equals("FALSE"))
                throw new IllegalArgumentException("All Day Event boolean incorrectly formatted");
            if (!newfields.get(8).equals("TRUE") && !newfields.get(8).equals("FALSE"))
                throw new IllegalArgumentException("Private boolean incorrectly formatted");

            title = newfields.get(0);
            startdate = LocalDate.parse(newfields.get(1), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            if (!Boolean.parseBoolean(newfields.get(5))) {
                starttime = LocalTime.parse(newfields.get(2), DateTimeFormatter.ofPattern("hh:mm a"));
                endtime = LocalTime.parse(newfields.get(4), DateTimeFormatter.ofPattern("hh:mm a"));
                isallday = false;
            }
            else {
                starttime = null;
                endtime = null;
                isallday = true;
            }
            enddate = LocalDate.parse(newfields.get(3), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            description = newfields.get(6);
            location = newfields.get(7);
            isprivate = Boolean.parseBoolean(newfields.get(8));

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
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

        return "\"" + returnlist.get(0) + "\",\"" +
                returnlist.get(1) + "\",\"" +
                returnlist.get(2) + "\",\"" +
                returnlist.get(3) + "\",\"" +
                returnlist.get(4) + "\",\"" +
                returnlist.get(5) + "\",\"" +
                returnlist.get(6) + "\",\"" +
                returnlist.get(7) + "\",\"" +
                returnlist.get(8) + "\"";
    }



}
