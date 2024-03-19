package org.kehadiransiswa.data;

public class ClassRoom {
    private int id;
    private int courseId;
    private String date;
    private String time;
    private String duration;
    private String location;

    public ClassRoom(int courseId, String date, String time, String duration, String location) {
        this.setCourseId(courseId);
        this.setDate(date);
        this.setTime(time);
        this.setDuration(duration);
        this.setLocation(location);
    }

    public ClassRoom(int id, int courseId, String date, String time, String duration, String location) {
        this.setId(id);
        this.setCourseId(courseId);
        this.setDate(date);
        this.setTime(time);
        this.setDuration(duration);
        this.setLocation(location);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
