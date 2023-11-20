package authenticationpkg;

import java.io.Serializable;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AccountManager implements Serializable {
	private HashMap<String, AuthUser> accountDict;
	private static final AccountManager accountManager = new AccountManager();
	private static final String filename = "CAMS Application/src/main/resources/AccountManager.dat";

	private AccountManager() {
		accountDict = readSerializedObject();
	}

	public void writeSerializedObject() {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {

			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(accountDict);
			out.close();
			System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

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

	public static AccountManager getInstance() {
		return accountManager;
	}

	public HashMap<String, AuthUser> getAccountDict() {
		return accountDict;
	}

	public AuthUser getAccount(String userID) {
		return accountDict.get(userID);
	}

	public void changePassword(AuthUser user, String newPassword) {
		user.setPassword(newPassword);
	}

	public void changeName(AuthUser user, String newName) {
		user.setName(newName);
	}

	public void changeAccountType(AuthUser user, AuthUser newUser) {
		accountDict.put(user.getUserID(), newUser);
	}

	public void toString(AuthUser user) {
		System.out.println("Name: " + user.getName());
		System.out.println("AccountType: " + user.getAccountType());
		System.out.println("userID: " + user.getUserID());
		System.out.println("Password: " + user.getPassword());
		System.out.println("Faculty: " + user.getFaculty());

	}

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
