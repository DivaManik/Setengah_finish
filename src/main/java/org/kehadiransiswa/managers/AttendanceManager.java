package org.kehadiransiswa.managers;

import org.kehadiransiswa.data.AttendanceRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManager {
    private Connection connection;
    List<AttendanceRecord> attendanceRecords;

    public AttendanceManager() {
        connection = DBConnectionManager.getConnection();
    }

    public List<AttendanceRecord> getAttendanceRecord() {
        List<AttendanceRecord> listofattendanceManager = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM attendance_records");
            while (rs.next()) {
                int id = rs.getInt("id");
                int ClassId = rs.getInt("class_id");
                int UserId = rs.getInt("user_id");
                String Status = rs.getString("status");
                listofattendanceManager.add(new AttendanceRecord(id, ClassId, UserId, Status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return listofattendanceManager;
    }

    public boolean recordAttendance(int classId, int userId, String status) {
        AttendanceRecord newAttendaceRecord = new AttendanceRecord(attendanceRecords.size() + 1, classId, userId, status);
        for (AttendanceRecord record :
                attendanceRecords) {
            if (record.getClassId() == newAttendaceRecord.getClassId()
                    && record.getUserId() != newAttendaceRecord.getUserId()) {
                attendanceRecords.add(newAttendaceRecord);
                try {
                    PreparedStatement statement = connection.prepareStatement("INSERT into attendance_records (class_id,user_id,status) values (? ,? ,?)");
                    statement.setInt(1,classId);
                    statement.setInt(2,userId);
                    statement.setString(3,status);
                }catch (SQLException e){
                    e.printStackTrace();
                    System.exit(1);
                }
                return true;
            }
        }
        return false;
    }
    //test koneksi
    public static void main(String[] args) {
        AttendanceManager am = new AttendanceManager();
        for (AttendanceRecord a : am.getAttendanceRecord()) {
            System.out.println(a.getClassId());
            System.out.println(a.getUserId());
            System.out.println(a.getStatus());
        }
    }
}
