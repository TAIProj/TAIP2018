package beans;

public class PlainTextDate {

    private String day;
    private String month;
    private String dayname;

    public PlainTextDate(String day, String month, String dayname) {
        this.day = day;
        this.month = month;
        this.dayname = dayname;
    }

    public PlainTextDate() {}

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayname() {
        return dayname;
    }

    public void setDayname(String dayname) {
        this.dayname = dayname;
    }
}
