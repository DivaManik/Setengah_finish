package org.kehadiransiswa.data;

public class AttendanceRecord {
    private int id;
    private int classId;
    private int userId;
    private String status; // Present, Absent, Late

    public AttendanceRecord(int id, int classId, int userId, String status) {
        this.setId(id);
        this.setClassId(classId);
        this.setUserId(userId);
        this.setStatus(status);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
