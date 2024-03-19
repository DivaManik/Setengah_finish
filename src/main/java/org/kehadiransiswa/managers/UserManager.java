package org.kehadiransiswa.managers;

import org.kehadiransiswa.data.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection connection;

    List<User> users;

//    public static void main(String[] args) {
//        UserManager usr = new UserManager();
//        for (User user : usr.getAllUsers()) {
//            System.out.println(user.getEmail());
//        }
//    }
    public UserManager() {
        connection = DBConnectionManager.getConnection();
        users = new ArrayList<>();
    }

    public List<User>getAllUsers(){
        List<User>listOfUser = new ArrayList<User>();

        try {
            Statement statement =connection.createStatement();
            ResultSet rs =statement.executeQuery("SELECT * from users");
            while (rs.next()){
                int id =rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role  = rs.getString("role");
                listOfUser.add(new User(id, username, password, email, role));
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        return listOfUser;
    }

    public boolean registerUser(String username, String password, String email, String role) {
        User newUser = new User(users.size()+1, username, password, email, role);
        if(users.isEmpty()){
            users.add(newUser);
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users(username,password,email,role) values (?,?,?,?)");
                statement.setString(1,username);
                statement.setString(2,password);
                statement.setString(3,email);
                statement.setString(4,role);
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
                System.exit(1);
            }
            return true;
        }
        for (User user : users) {
            if (!user.getEmail().equalsIgnoreCase(newUser.getEmail())) {
                users.add(newUser);
                return true;
            }
        }

        return false;
    }

    public boolean updateProfile(int id, String username, String password, String email, String role) {
        User newUser = new User(id, username, password, email, role);
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE id = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, role);
            statement.setInt(5, id);

            // Jalankan pernyataan untuk memperbarui profil pengguna
            int rowsAffected = statement.executeUpdate();

            // Tutup pernyataan
            statement.close();
            if (rowsAffected > 0) {
                return true; // Mengembalikan true karena profil pengguna berhasil diperbarui
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == newUser.getId()) {
                users.set(i, newUser);
                return true;
            }
        }
        return false;
    }


    public List<User> getAllUser() {
        return users;
    }
}
