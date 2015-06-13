/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;

/**
 *
 * @author Travis
 */
public class User {

    private static boolean loggedIn = false;
    private static int userID = -1;
    private static String userName = "Anonymous";

    //Set user ID number
    //@param user ID number
    public static void login(int userID, String userName) {
        User.loggedIn = true;
        User.userID = userID;
        User.userName = userName;
    }

    //set login statuc
    //@param login status
    public static void logout() {
        User.loggedIn = false;
        User.userName = "Anonymous";
        User.userID = -1;
    }

    //get login status
    //@return true if logged in
    public static boolean isLoggedIn() {
        return loggedIn;
    }

    //get user ID number
    //@return user id number
    public static int getUserID() {
        return userID;
    }

    //get user display name
    //@return user handle
    public static String getUserName() {
        return userName;
    }
    
    public static void setUserName(String userName){
        User.userName = userName;
    }
    

}
