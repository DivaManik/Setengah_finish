package org.kehadiransiswa.managers;


import org.kehadiransiswa.data.ClassRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassManager {
    List<ClassRoom> classRooms = new ArrayList<>();
    private Connection connection;

//   tes koneksi
    public ClassManager() {
        connection =DBConnectionManager.getConnection();
        classRooms = new ArrayList<>();
    }

//
    public List<ClassRoom> getAllClass(){
        List<ClassRoom> listOfClass =new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from classes");
            while (rs.next()) {
                int id = rs.getInt("id");
                int course_id = rs.getInt("course_id");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String duration = rs.getString("duration");
                String location = rs.getString("location");
                listOfClass.add(new ClassRoom(id, course_id, date, time, duration, location));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        return listOfClass;
    }

    // Add methods for class management (schedule, cancel, update)
    public boolean scheduleClass(int courseId, String date, String time, String duration, String location) {
        ClassRoom newClassRoom = new ClassRoom(classRooms.size() + 1 , courseId, date, time, duration, location);
        if (!classRooms.contains(newClassRoom)) {
            classRooms.add(newClassRoom);
            try{
                PreparedStatement statement =connection.prepareStatement("INSERT INTO classes (course_id, date, time, duration, location) VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1, courseId);
                statement.setString(2, date);
                statement.setString(3, time);
                statement.setString(4, duration);
                statement.setString(5, location);
                int rowsAffected = statement.executeUpdate();

                // Tutup pernyataan
                statement.close();

                // Jika ada baris yang terpengaruh, artinya kelas berhasil dijadwalkan
                if (rowsAffected > 0) {
                    // Jika berhasil, tambahkan kelas ke koleksi lokal
                    ClassRoom newClassRooms = new ClassRoom(classRooms.size() + 1, courseId, date, time, duration, location);
                    classRooms.add(newClassRooms);

                    return true; // Mengembalikan true karena kelas berhasil dijadwalkan
                }
            }catch (SQLException e){
                e.printStackTrace();
                System.exit(1);
            }
        }
        return false;

    }

    public boolean cancelClass(int classId) {
        int indexToDelete = -1;

        for (int i = 0; i < classRooms.size(); i++) {
            if (classRooms.get(i).getId() == classId) {
                indexToDelete = i;
            }
        }
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM classes WHERE id = ?");
            statement.setInt(1, classId);

            // Jalankan pernyataan untuk menghapus kelas
            int rowsAffected = statement.executeUpdate();

            // Tutup pernyataan
            statement.close();
            if (rowsAffected > 0) {
                return true; // Mengembalikan true karena kelas berhasil dibatalkan
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        if (indexToDelete > 0) {
            classRooms.remove(indexToDelete);
            return true;
        }
        return false;

    }

    public boolean updateClass(int classId, int courseId, String newDate, String newTime, String newDuration, String newLocation) {
        ClassRoom newClassRoom = new ClassRoom(classId,courseId,newDate, newTime, newDuration, newLocation);
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE classes SET course_id = ?, date = ?, time = ?, duration = ?, location = ? WHERE id = ?");
            statement.setInt(1, courseId);
            statement.setString(2, newDate);
            statement.setString(3, newTime);
            statement.setString(4, newDuration);
            statement.setString(5, newLocation);
            statement.setInt(6, classId);
            int rowsAffected = statement.executeUpdate();
            statement.close();
            if (rowsAffected > 0) {
                // Perbarui koleksi lokal jika perlu
                for (int i = 0; i < classRooms.size(); i++) {
                    if (classRooms.get(i).getId() == classId) {
                        ClassRoom newClassRooms = new ClassRoom(classId, courseId, newDate, newTime, newDuration, newLocation);
                        classRooms.set(i, newClassRooms);
                        break;
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        for (int i = 0; i < classRooms.size(); i++) {
            if(classRooms.get(i).getId() == classId){
                classRooms.set(i, newClassRoom);
            }
        }
        return false;

    }

    public List<ClassRoom> getAllClasses() {
        return getAllClass();
    }

    public static void main(String[] args) {
        ClassManager cls = new ClassManager();
        for (ClassRoom clas : cls.getAllClass()){
            System.out.println(clas.getId());
            System.out.println(clas.getCourseId());
            System.out.println(clas.getTime());
            System.out.println(clas.getDuration());
            System.out.println(clas.getLocation());
        }

    }
}
