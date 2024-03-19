package org.kehadiransiswa.data;

public class Course {
    private int id;
    private String title;
    private String description;

    public Course(String title, String description) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
    }

    public Course(int id, String title, String description) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}