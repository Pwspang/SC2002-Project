package authenticationpkg;
import camppkg.*;

public class AuthCCMember extends AuthStudent{
    public AuthCCMember(String name, String userID, String password, Faculty faculty){
        super(name, userID, password, faculty);
    }

}
