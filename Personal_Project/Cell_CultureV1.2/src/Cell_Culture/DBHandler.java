/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Travis
 */
public class DBHandler {
        
    //class server
    static final String DB_URL = "jdbc:mysql://209.129.8.4:3306/42029";
    static final String DB_USER = "42029";
    static final String DB_PASS = "42029csc18b";


    /**
     * check for existing email
     *
     * @param email to check
     * @return number in rows including email
     */
    public static int checkEmail(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numRows = 0;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            stmt = conn.prepareStatement("SELECT count(*) FROM `TH1998726_entity_players` WHERE `email` = ?;");

            stmt.setString(1, email.trim());
            rs = stmt.executeQuery();

            while (rs.next()) {
                numRows = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(rs, stmt, conn);
        }

        return numRows;
    }

    /**
     * add user information to database
     *
     * @param fName User First Name
     * @param lName User Last Name
     * @param userName user name
     * @param email User email
     * @param pass User password
     * @return number of updated records
     */
    public static int register(String fName, String lName, String userName, String email, String pass) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numRows = 0;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement st = conn.prepareStatement("INSERT INTO "
                            + "TH1998726_entity_players("
                            + "first_name,"
                            + "last_name,"
                            + "email,"
                            + "user_name,"
                            + "password) "
                            + "VALUES (?,?,?,?,?)");

            st.setString(1, fName.trim());
            st.setString(2, lName.trim());
            st.setString(3, email.trim());
            st.setString(4, userName.trim());
            st.setString(5, pass.trim());
                    
            numRows = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(rs, stmt, conn);
        }
        return numRows;
    }

    /**
     * check login credentials, login if match
     *
     * @param email User email
     * @param pass User password
     * @return return true if login valid
     */
    public static boolean login(String email, String pass) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int numRows = 0;
        int id = 1;
        String userName = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            stmt = conn.prepareStatement("SELECT `player_id`, `user_name` FROM `TH1998726_entity_players` WHERE `email` = ? AND `password` =  ?;");
            stmt.setString(1, email.trim());
            stmt.setString(2, pass.trim());

            rs = stmt.executeQuery();

            while (rs.next()) {
                numRows++;
                id = rs.getInt("player_id");
                userName = rs.getString("user_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(rs, stmt, conn);
        }
        if (numRows == 1) {
            User.login(id, userName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Submit score to database
     *
     * @param userID user playerID
     * @param score game score
     * @param kills number of kill this game
     * @param powerUps number of powerUps collected
     * @param deaths number of deaths this game
     */
    public static void submitScore(int userID, int score, int kills, int powerUps, int deaths) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
      //  int scoreID = 0;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            stmt = conn.prepareStatement("INSERT INTO `TH1998726_enum_scores` (player_id, score) VALUES (?,?)");
            stmt.setInt(1, userID);
            stmt.setInt(2, score);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            closeConnections(rs, stmt, conn);
        }

    }

    

    /**
     * close database connection variables
     *
     * @param rs MySql ResultSet
     * @param stmt MySql Statement
     * @param conn MySql connection
     */
    private static void closeConnections(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
