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
        attendanceRecords = new ArrayList<>();
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

        try {
            // Persiapkan pernyataan untuk menyisipkan catatan kehadiran ke dalam database
            PreparedStatement statement = connection.prepareStatement("INSERT INTO attendance_records (class_id, user_id, status) VALUES (?, ?, ?)");
            statement.setInt(1, classId);
            statement.setInt(2, userId);
            statement.setString(3, status);

            // Jalankan pernyataan untuk menyisipkan catatan kehadiran
            int rowsAffected = statement.executeUpdate();

            // Tutup pernyataan
            statement.close();

            // Jika operasi insert berhasil (ada baris yang terpengaruh), kembalikan true
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }


        for (AttendanceRecord record :
                attendanceRecords) {
            if (record.getClassId() == newAttendaceRecord.getClassId()
                    && record.getUserId() != newAttendaceRecord.getUserId()) {
                attendanceRecords.add(newAttendaceRecord);
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
