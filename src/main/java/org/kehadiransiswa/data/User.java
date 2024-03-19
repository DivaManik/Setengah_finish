package org.kehadiransiswa.data;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    public static final String ADMIN = "ADMIN";
    public static final String STUDENT = "STUDENT";
    public static final String TEACHER = "TEACHER";
    public User( String username, String password, String email, String role) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setRole(role);
    }

    public User(int id, String username, String password, String email, String role) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setRole(role);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}