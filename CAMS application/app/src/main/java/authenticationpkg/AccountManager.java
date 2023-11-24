package authenticationpkg;

import java.io.Serializable;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/***
 * Used to store all AuthUser objects.
 * Contains functions related to AuthUsers.
 */
public class AccountManager implements Serializable {

	/**
	 * A HashMap with userID as key and AuthUser for value.
	 * This HashMap will contain all accounts in the CAMS system.
	 */
	private HashMap<String, AuthUser> accountDict;

	/**
	 * An instance of AccountManager.
	 */
	private static final AccountManager accountManager = new AccountManager();

	/**
	 * File path that AccountManager reads and writes to.
	 */
	private static final String filename = "src/main/resources/AccountManager.dat";

	/**
	 * Constructor method used to construct AccountManager.
	 * Initialises the accountDict from the AccountManager.dat file.
	 */
	private AccountManager() {
		accountDict = readSerializedObject();
	}

	/**
	 * Used to write serialised accountDict to the AccountManager.dat file.
	 */
	public void writeSerializedObject() {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(accountDict);
			out.close();
			System.out.println("Account Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Reads from the AccountManager.dat file to initialise accountDict.
	 * 
	 * @return HashMap object with userID as key, AuthUser as value.
	 */
	public HashMap<String, AuthUser> readSerializedObject() {
		HashMap<String, AuthUser> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (HashMap<String, AuthUser>) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		// print out the size
		// System.out.println(" Details Size: " + pDetails.size());
		// System.out.println();
		return pDetails;
	}

	/**
	 * Gets an instance of the AccountManager.
	 * Ensures that only a single instance of AccountManager is created.
	 * 
	 * @return AccountManager object
	 */
	public static AccountManager getInstance() {
		return accountManager;
	}

	/**
	 * Gets the accountDict of AccountManager.
	 * 
	 * @return HashMap object with userID as key, AuthUser as value.
	 */
	public HashMap<String, AuthUser> getAccountDict() {
		return accountDict;
	}

	/**
	 * Gets the AuthUser associated to userID.
	 * 
	 * @param userID AuthUser's userID.
	 * @return AuthUser object that is associated with userID
	 */
	public AuthUser getAccount(String userID) {
		return accountDict.get(userID);
	}

	/**
	 * Changes the current password of the AuthUser to newPassword.
	 * 
	 * @param user        AuthUser object.
	 * @param newPassword new password to change to.
	 */
	public void changePassword(AuthUser user, String newPassword) {
		user.setPassword(newPassword);
	}

	/**
	 * Changes the current name of the AuthUser to newName.
	 * 
	 * @param user    AuthUser object.
	 * @param newName new name to change to.
	 */
	public void changeName(AuthUser user, String newName) {
		user.setName(newName);
	}

	/**
	 * Changes the account type of AuthUser object from user to newUser.
	 * 
	 * @param user    Current AuthUser object.
	 * @param newUser New AuthUser object to change to.
	 */
	public void changeAccountType(AuthUser user, AuthUser newUser) {
		accountDict.put(user.getUserID(), newUser);
	}

	/**
	 * Prints the account details associated with the AuthUser user object.
	 * 
	 * @param user AuthUser object.
	 */
	public void toString(AuthUser user) {
		System.out.println("Name: " + user.getName());
		System.out.println("AccountType: " + user.getAccountType());
		System.out.println("userID: " + user.getUserID());
		System.out.println("Password: " + user.getPassword());
		System.out.println("Faculty: " + user.getFaculty());

	}

	/**
	 * Gets the AuthUser object associated with the userID and password.
	 * 
	 * @param userID   userID used to login.
	 * @param password Password used to login.
	 * @return AuthUser object associated with userID on successful login; throws an
	 *         error if unsuccessful.
	 */
	public AuthUser login(String userID, String password) {
		if (!accountDict.containsKey(userID))
			throw new IllegalArgumentException("User does not exist");
		System.out.println(getAccount(userID).getPassword());
		if (!password.equals(getAccount(userID).getPassword())) {
			throw new IllegalArgumentException("Incorrect Password");
		}

		return getAccount(userID);
	}

}
