package Handlers;
import javax.swing.*;
import java.sql.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class DataBase {
    /**
     * Connect to a sample database
     */
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\almog\\IdeaProjects\\CodeBinder\\MyDatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
        return conn;
    }//End connect()


    public static void createNewTable(String name) {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\almog\\IdeaProjects\\CodeBinder\\MyDatabase.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS "+name+" (\n"
                + "    Name text PRIMARY KEY,\n"
                + "    Path text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
    }

    public static void insert(String Name, String Path, String tableName) {
        String sql = "INSERT INTO $tbName (Name,Path) VALUES(?,?)";
        String query =sql.replace("$tbName",tableName);
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, Name);
            pstmt.setString(2, Path);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
    }

    public static void delete(String Name, String tableName) {
        String sql = "DELETE FROM $tbName WHERE name = ?";
        String query =sql.replace("$tbName",tableName);
        try (Connection conn = connect(); //try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, Name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
    }

    public static void updateSubject(int id, String NewName) {
        String sql = "UPDATE Subjects SET Name = ? "
                + "WHERE Id = ?";
        try (Connection conn =connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, NewName);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
    }

    public static void UpdateHeadline(int subjectId, int Id, String Name, String Path){
        String sql = "UPDATE Headlines SET Name = ?, Path = ? WHERE Subject_Id = ? AND Id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Name);
            pstmt.setString(2, Path);
            pstmt.setInt(3, subjectId);
            pstmt.setInt(4, Id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
    }

    public static ResultSet SelectFromTable(String table){
        Connection con = connect();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
        ResultSet rs = null;
        if (stmt != null) {
            try {
                rs = stmt.executeQuery("select * from "+table);
            } catch (SQLException e) {
                showMessageDialog(null, "Error");
            }
        }
        return rs;
    }

    public static ResultSet SelectFromHeadlinesTable(int SubjectId){
        Connection con = connect();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
        ResultSet rs = null;
        if (stmt != null) {
            try {
                rs = stmt.executeQuery("select * from Headlines WHERE Subject_Id = '"+SubjectId+"'");
            } catch (SQLException e) {
                showMessageDialog(null, "Error");
            }
        }
        return rs;
    }

    //we only use 1 string so this is the return type
    public static String SelectDirectory(){
        Connection con = connect();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
        ResultSet rs = null;
        if (stmt != null) {
            try {
                rs = stmt.executeQuery("select Path from FilesInfo");
            } catch (SQLException e) {
                showMessageDialog(null, "Error");
            }
        }
        String s = null;
        try {
            while (rs.next()) {
                s=rs.getString(1);
            }
        } catch (SQLException ex) {showMessageDialog(null, "Error");}
        return s;
    }

    public static void updateDirectory(String path){
        String sql = "UPDATE FilesInfo SET Path = ? WHERE First = 1";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, path);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            showMessageDialog(null, "Error");
        }
    }

}
