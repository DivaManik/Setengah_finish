package org.kehadiransiswa.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*

The design pattern used in the DBConnectionManager class is the Singleton Pattern.

Singleton Pattern:
The Singleton Pattern ensures that a class has only one instance and provides a global point of access to that instance.
It is commonly used when you want to control access to resources, manage global state, or ensure that a class has a single point of access.

In the DBConnectionManager class:
The constructor is marked as private to prevent external instantiation of the class.
The getConnection() method is static, allowing access to the single instance of the class without needing to create an object.
The getConnection() method checks if the connection object is null. If it is null, it establishes a new database connection.
Otherwise, it returns the existing connection.
The closeConnection() method allows closing the database connection when it's no longer needed.
Here's how the Singleton Pattern is applied in the DBConnectionManager class:

Private Constructor: By making the constructor private, we prevent external classes from creating instances of DBConnectionManager.
This ensures that only one instance of DBConnectionManager exists.
Static Method for Access: The getConnection() method is static, allowing access to the single instance of DBConnectionManager
without needing to create an object. This method follows the lazy initialization approach, creating the connection only
when it is requested for the first time.
Singleton Instance: The connection variable is static, ensuring that there is only one instance
of the database connection shared across the application.

By using the Singleton Pattern, we ensure that there is only one instance of the DBConnectionManager class throughout the application,
and all parts of the application can access the same database connection. This helps in managing resources efficiently
and maintaining a consistent state across the application.
* */
public class DBConnectionManager {
    private Connection koneksi;
    static DBConnectionManager dbConnectionManager;
    private DBConnectionManager(){
    try{
        String dbURL = "jdbc:sqlite:attendance.db";
        koneksi=DriverManager.getConnection(dbURL);
    } catch (SQLException e){
        e.printStackTrace();
        System.exit(1);
    }
    }

    public static Connection getConnection(){
        if (dbConnectionManager == null)
            dbConnectionManager =new DBConnectionManager();
        return dbConnectionManager.koneksi;
    }
}
