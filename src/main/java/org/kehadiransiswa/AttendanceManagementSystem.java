package org.kehadiransiswa;


import org.kehadiransiswa.data.ClassRoom;
import org.kehadiransiswa.data.Course;
import org.kehadiransiswa.managers.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AttendanceManagementSystem {
    AttendanceManager attendanceManager;
    UserManager userManager;
    CourseManager courseManager;
    ClassManager classManager;

    public AttendanceManagementSystem() {
        attendanceManager = new AttendanceManager();
        userManager = new UserManager();
        courseManager = new CourseManager();
        classManager = new ClassManager();
    }

    public void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Attendance Management System");
            System.out.println("0. Exit");
            System.out.println("1. Login");
            System.out.println("2. User Register");
            System.out.println("3. View All Courses");
            System.out.println("4. Add Course");
            System.out.println("5. Edit Course");
            System.out.println("6. Delete Course");
            System.out.println("7. View All Class Room");
            System.out.println("8. Add a  Class Room");
            System.out.println("9. Update a  Class Room");
            System.out.println("10. Cancel a  Class Room");
            System.out.println("11. Record Attendance");
            System.out.println("12. Generate Report");
            System.out.print("Choose an option: ");

            int choice = 99;
            try {
                choice = Integer.parseInt(scanner.next());
            } catch (NumberFormatException | NullPointerException e) {
                System.out.print("Input should be number!\n");
            }

            switch (choice) {
                case 0:
                    exitApps();
                    break;
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    viewAllCourses();
                    break;
                case 4:
                    addCourse(scanner);
                    break;
                case 5:
                    editCourse(scanner);
                    break;
                case 6:
                    deleteCourse(scanner);
                    break;
                case 7:
                    viewAllClasses();
                    break;
                case 8:
                    scheduleClass(scanner);
                    break;
                case 9:
                    updateClass(scanner);
                    break;
                case 10:
                    cancelClass(scanner);
                    break;
                case 11:
                    recordAttendance(scanner);
                    break;
                case 12:
                    generateAttendanceReport(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void exitApps() {
        System.out.println("Keluar aplikasi. Goodbye!");
        System.exit(0);
    }

    private void login(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();


    }

    private void register(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter role: ");
        String role = scanner.nextLine();

        if (userManager.registerUser(username, password, email, role)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private void viewAllCourses() {
        List<Course> courses = courseManager.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("List of Courses:");
            for (Course course : courses) {
                System.out.println(course.getId() + ". " + course.getTitle() + " - " + course.getDescription());
            }
        }
    }

    private void addCourse(Scanner scanner) throws SQLException {
        scanner.skip("\\R?");
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine();

        if (courseManager.addCourse(title, description)) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Failed to add course. Please try again.");
        }
    }

    private void editCourse(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter course ID to edit: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new course title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new course description: ");
        String newDescription = scanner.nextLine();

        if (courseManager.editCourse(courseId, newTitle, newDescription)) {
            System.out.println("Course edited successfully!");
        } else {
            System.out.println("Failed to edit course. Please check the course ID and try again.");
        }
    }

    private void deleteCourse(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter course ID to delete: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (courseManager.deleteCourse(courseId)) {
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("Failed to delete course. Please check the course ID and try again.");
        }
    }

    private void viewAllClasses() {
        List<ClassRoom> classes = classManager.getAllClasses();
        if (classes.isEmpty()) {
            System.out.println("No classes found.");
        } else {
            System.out.println("List of Classes:");
            for (ClassRoom scheduledClass : classes) {
                System.out.println(scheduledClass.getId() + ". Course ID: " + scheduledClass.getCourseId() +
                        " Date: " + scheduledClass.getDate() + " Time: " + scheduledClass.getTime() +
                        " Duration: " + scheduledClass.getDuration() + " Location: " + scheduledClass.getLocation());
            }
        }
    }

    private void scheduleClass(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM): ");
        String time = scanner.nextLine();
        System.out.print("Enter duration: ");
        String duration = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        if (classManager.scheduleClass(courseId, date, time, duration, location)) {
            System.out.println("Class scheduled successfully!");
        } else {
            System.out.println("Failed to schedule class. Please try again.");
        }
    }

    private void cancelClass(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter class ID to cancel: ");
        int classId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (classManager.cancelClass(classId)) {
            System.out.println("Class canceled successfully!");
        } else {
            System.out.println("Failed to cancel class. Please check the class ID and try again.");
        }
    }

    private void updateClass(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter class ID to update: ");
        int classId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();
        System.out.print("Enter new time (HH:MM): ");
        String newTime = scanner.nextLine();
        System.out.print("Enter new duration: ");
        String newDuration = scanner.nextLine();
        System.out.print("Enter new location: ");
        String newLocation = scanner.nextLine();

        if (classManager.updateClass(classId, courseId, newDate, newTime, newDuration, newLocation)) {
            System.out.println("Class updated successfully!");
//            System.out.println("Failed to update class. Please check the class ID and try again.");

        } else {
            System.out.println("Failed to update class. Please check the class ID and try again.");
//            System.out.println("Class updated successfully!");

        }
    }

    private void recordAttendance(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter class ID: ");
        int classId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter status (Present/Absent): ");
        String status = scanner.nextLine();

        if (attendanceManager.recordAttendance(classId, studentId, status)) {
            System.out.println("Attendance recorded successfully!");
        } else {
            System.out.println("Failed to record attendance. Please try again.");
        }
    }

    private void generateAttendanceReport(Scanner scanner) {
        scanner.skip("\\R?");
        System.out.print("Enter class ID: ");
        int classId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    }

    public static void main(String[] args) throws SQLException {
        AttendanceManagementSystem attendanceManagementSystem = new AttendanceManagementSystem();
        attendanceManagementSystem.start();
    }
}