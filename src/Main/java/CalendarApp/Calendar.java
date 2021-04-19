package CalendarApp;

import javafx.scene.layout.GridPane;

import static java.lang.Math.floor;

public class Calendar extends GridPane {

    private int month;
    private int year;
    private final User user;

    /** Creates a Calendar in a GridPane
     *
     * @param month current month being displayed
     * @param year current year being displayed
     */
    public Calendar(int month, int year, User user){
        this.month = month;
        this.year = year;
        this.user = user;
        populateCalendar(month, year);
    }

    /** Populates Calendar with Day boxes
     *
     * @param month current month being displayed
     * @param year current year being displayed
     */
    public void populateCalendar(int month, int year){
        // Clear out current calendar
        getChildren().clear();

        int firstday = startingDay(month, year);
        int lastmonth = month - 1;
        int currentday;
        boolean writingcurrentmonth = false;

        // February has 29 days if leap year
        if (isLeapYear(year)) monthdays[2] = 29;

        // Determines first day to print
        if (firstday == 0) {
            writingcurrentmonth = true;
            currentday = 1;
        } else
            currentday = monthdays[month-1] - firstday + 1;

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++){
                if (writingcurrentmonth)
                    add(new CalendarBox(currentday, month, year, user), j, i);
                else
                    add(new CalendarBox(currentday, month-1, year, user), j, i);
                if (currentday < monthdays[lastmonth] && !writingcurrentmonth){
                    currentday++;
                }
                else if (currentday >= monthdays[lastmonth] && !writingcurrentmonth){
                    currentday = 0;
                    writingcurrentmonth = true;
                }
                if (currentday < monthdays[month] && writingcurrentmonth){
                    currentday++;
                }
                else if (currentday >= monthdays[month] && writingcurrentmonth){
                    currentday = 1;
                    writingcurrentmonth = false;
                }
            }
    }
    public void populateCalendar(){
        populateCalendar(month, year);
    }

    // Starts at December for looping purposes
    private static final int[] monthdays = new int[]{31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // Contains codes based on Zeller's Congruence algorithm
    private static final int[] monthcode = new int[]{11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private static final String[] months = new String[]{null ,"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    /** Determines first day of month using Zeller's Congruence algorithm
     *
     * @param month current month being displayed
     * @param year current year being displayed
     * @return day of week in number form (0-6)
     */
    private static int startingDay(int month, int year) {
        int k = 1;
        int m = monthcode[month-1];
        if (month == 1 || month == 2)
            year -= 1;
        int D = year % 100;
        int C = year / 100;
        int F = (k+((13*m-1)/5)+D+(D/4)+(C/4)-2*C);
        if (F >= 0)
            return F % 7;
        else // function to mod a negative number
            return (int)(F - (floor(F / 7.0) * 7));
    }

    /** Determines if a given year is a leap year
     *
     * @param year current year being displayed
     */
    private static boolean isLeapYear(int year){
        if ((year % 4) == 0)
            return true;
        else return (year & 400) == 0;
    }

    public String dateToString(){
        return months[month] + ", " + year;
    }

    public void moveMonthForwards(){
        if (month == 12) {
            month = 1;
            year++;
        } else month++;
        populateCalendar(month, year);
    }

    public void moveMonthBackwards(){
        if (month == 1) {
            month = 12;
            year--;
        } else month--;
        populateCalendar(month, year);
    }

}
