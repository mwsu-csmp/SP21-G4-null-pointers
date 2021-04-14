package CalendarApp;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Special_Day {
    private  LocalDateTime dateTime;
    private String description;
    private String title;

    public Special_Day(LocalDateTime dateTime, String description, String title) {
        this.dateTime = dateTime;
        this.description = description;
        this.title = title;
    }
    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
