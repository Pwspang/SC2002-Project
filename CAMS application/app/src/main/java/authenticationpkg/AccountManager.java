package authenticationpkg;

import java.io.Serializable;
import java.util.HashMap;

public class AccountManager implements Serializable {
	private HashMap<String, AuthUser> accountDict;
	private static final AccountManager accountManager = new AccountManager();
	private static final String filename = "AccountManager.dat";

	private AccountManager() {
		accountDict = readSerializedObject(filename);

	}

	public static void writeSerializedObject() {
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

	public static HashMap<String, AuthUser> readSerializedObject() {
		Map<String, AuthUser> pDetails = null;
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

}
