package org.kehadiransiswa.managers;

import org.kehadiransiswa.data.Course;
import org.kehadiransiswa.data.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    List<Course> courses = new ArrayList<>();
    private Connection connection;

    public CourseManager() {
        connection =DBConnectionManager.getConnection();
        courses = new ArrayList<>();
    }

    public static void main(String[] args) {
        CourseManager crs = new CourseManager();
        for (Course courses : crs.getAllCourse()){
            System.out.println(courses.getId());
            System.out.println(courses.getTitle());
            System.out.println(courses.getDescription());
        }
    }
    public List<Course> getAllCourse(){
        List<Course>listOfCourse = new ArrayList<>();
        try {
            Statement statement =connection.createStatement();
            ResultSet rs =statement.executeQuery("SELECT * from courses");
            while (rs.next()){
                int id = rs.getInt("id");
                String title= rs.getString("title");
                String desc = rs.getString("description");
                listOfCourse.add(new Course(id,title,desc));
            }
            rs.close();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        return listOfCourse;
    }



    // Add methods for course management (create, edit, delete)
    public boolean addCourse(String title, String description) {
        Course newCourse = new Course(courses.size() + 1, title, description);

        if (!courses.contains(newCourse)) {
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO courses(title,description) values (?,?)");
                statement.setString(1,title);
                statement.setString(2,description);
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
                System.exit(1);
            }
            courses.add(newCourse);
            return true;
        }
        return false;
    }


    public boolean editCourse(int courseId, String newTitle, String newDescription) {
        Course newCourse = new Course(courseId, newTitle, newDescription);
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE courses SET title = ?, description = ? WHERE id = ?");
            statement.setString(1, newTitle);
            statement.setString(2, newDescription);
            statement.setInt(3, courseId);

            // Jalankan pernyataan untuk memperbarui kursus
            int rowsAffected = statement.executeUpdate();

            // Tutup pernyataan
            statement.close();
            if (rowsAffected > 0) {
                // Perbarui koleksi lokal jika perlu
                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getId() == courseId) {
                        Course newCourses = new Course(courseId, newTitle, newDescription);
                        courses.set(i, newCourses);
                        break;
                    }
                }
                return true; // Mengembalikan true karena kursus berhasil diperbarui
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                courses.set(i, newCourse);
            }
        }
        return false;

    }

    public boolean deleteCourse(int courseId) {
        int indexToDelete = -1;
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM courses WHERE id = ?");
            statement.setInt(1, courseId);

            // Jalankan pernyataan untuk menghapus kursus
            int rowsAffected = statement.executeUpdate();

            // Tutup pernyataan
            statement.close();

            // Jika ada baris yang terpengaruh, artinya kursus berhasil dihapus
            if (rowsAffected > 0) {
                // Hapus kursus dari koleksi lokal jika perlu
                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getId() == courseId) {
                        courses.remove(i);
                        break;
                    }
                }
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }



        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                indexToDelete = i;
            }
        }
        if (indexToDelete > 0) {
            courses.remove(indexToDelete);
        }
        return false;

    }

    public List<Course> getAllCourses() {
        return courses;
    }
}
