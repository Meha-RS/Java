package Pract7.Task5;

public class CourseRecord {
    private final String title;
    private final int hours;

    public CourseRecord(String title, int hours) {
        this.title = title;
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + " (" + hours + " ч.)";
    }
}