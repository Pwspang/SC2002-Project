package authenticationpkg;
import java.io.Serializable;
import java.util.HashMap;

public class AccountManager implements Serializable{
    private Map<String,AuthUser> accountDict;
    private static AccountManager accountManager;

    
    public static void writeSerializedObject(String filename, Map<String, AuthUser> accountDict){
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

    public static void 



}







