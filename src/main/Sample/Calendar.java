package Sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.floor;

public class Calendar extends GridPane {

    private int month;
    private int year;

    /** Creates a Calendar in a GridPane
     *
     * @param month current month being displayed
     * @param year current year being displayed
     */
    public Calendar(int month, int year){
        super();
        this.month = month;
        this.year = year;
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
        if (month == 2 && isLeapYear(year)) monthdays[1] = 29;

        // Determines first day to print
        if (firstday == 0) {
            writingcurrentmonth = true;
            currentday = 1;
        } else
            currentday = monthdays[month-1] - firstday + 1;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++){
                add(calendarBox(new Label(String.valueOf(currentday)+ ".")), j, i);
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
    }

    // Starts at December for looping purposes
    private static final int[] monthdays = new int[]{31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     *  Contains codes based on Zeller's Congruence algorithm
     */
    private static final int[] monthcode = new int[]{11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private static final String[] months = new String[]{null ,"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    /** Creates a single calendar box
     *
     * @param label Current day number of box
     * @return Calendar box StackPane object
     */
    private static Pane calendarBox(Label label){
        StackPane stackPane = new StackPane();
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        stackPane.getChildren().addAll(rectangle, label);
        StackPane.setAlignment(label, Pos.TOP_LEFT);
        StackPane.setMargin(label, new Insets(4));
        return stackPane;
    }

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
        else if ((year & 400) == 0)
            return true;
        else
            return false;
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
