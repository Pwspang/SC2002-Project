package authenticationpkg;

import java.io.Serializable;

import feedbackpkg.*;
import camppkg.*;
import pointspkg.*;
import viewpkg.iView;

/**
 * AuthUser is an abstract class that represents the base class of all types of
 * user accounts.
 * It includes attributes: name, userID, password, faculty.
 * 
 */

public abstract class AuthUser implements Serializable {
    /**
     * The full name of the user that this user account belongs to.
     */
    private String name;

    /**
     * A unique userID is assigned to all users.
     * The userID cannot be modified.
     */
    private String userID;

    /**
     * The password used to login using this user account.
     */
    private String password;

    /**
     * The faculty that this user account belongs to.
     */
    private Faculty faculty;

    /**
     * Creates a new AuthUser with the given parameters.
     * This is an abstract class that cannot be instantiated.
     * 
     * @param name     This AuthUser's name.
     * @param userID   This AuthUser's userID.
     * @param password This AuthUser's password.
     * @param faculty  This faculty that this AuthUser belongs to.
     */

    public AuthUser(String name, String userID, String password, Faculty faculty) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
    }

    /**
     * Gets the account type of this user account.
     * 
     * @return this AuthUser's account type.
     */
    public String getAccountType() {
        return this.getClass().getName();

    }

    /**
     * Gets the full name of this user account.
     * 
     * @return this AuthUser's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique userID of this user account.
     * 
     * @return this AuthUser's userID.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Gets the password of this user account.
     * 
     * @return this AuthUser's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Changes the password of this user account.
     * 
     * @param newPassword This AuthUser's new password.
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Changes the name that this user account belongs to.
     * 
     * @param newName This AuthUser's new name.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the faculty of this user account.
     * 
     * @return This AuthUser's faculty.
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Changes the faculty of this user account.
     * 
     * @param newFaculty This AuthUser's new faculty.
     */
    public void setFaculty(Faculty newFaculty) {
        this.faculty = newFaculty;
    }

    /**
     * Gets the Camp object associated with campID
     * 
     * @param campID Camp object's campID
     * @return Camp object associated with the corresponding campID
     */
    public Camp getCamp(String campID) {
        CampManager campManager = CampManager.getInstance();
        return campManager.getCamp(campID);
    }

    /**
     * Gets the CampInfo object associated with campID
     * 
     * @param campID Camp object's campID
     * @return CampInfo object associated with the corresponding campID
     */
    public CampInformation getCampInfo(String campID) {
        CampManager campManager = CampManager.getInstance();
        return campManager.getCampInfo(campID);
    }

    /**
     * Gets the Feedback object associated with the corresponding feedbackID
     * 
     * @param feedbackID Feedback object's feedbackID
     * @return Feedback object associated with the corresponding feedbackID
     */
    public Feedback getFeedback(int feedbackID) throws Exception {
        FeedbackManager feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getFeedback(feedbackID);
    }

    public abstract iView getUI();

}
