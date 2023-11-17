package viewPackage;

public class campComitteeUI extends studentUI{
    private static campComitteeUI ui=null;
    private static String[] options = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};

    public static iView getInstance(){
        if (campComitteeUI.ui == null){
            campComitteeUI.ui = new campComitteeUI();
        }
        return campComitteeUI.ui;
    }

    public void handleOption(int option){};


}
