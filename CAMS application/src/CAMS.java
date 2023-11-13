// To import all the required packages

import viewPackage.*;


/**
 *
 */

public class CAMS {
    //To change to user
    private static int user=0;
    private static iView currView;

    public static void main(String[] args){
        start();
    }

    private static void start(){
        startAllManager();
        // Current state to call currView.displayOptions() on loop
        while (true){
            if (user == 0){
                login();
            } else {
                currView.displayOptions();
            }
        }
    }

    private static void login(){
        user=loginUI.handleLogin();

        //To Do set view based on user permission
        currView = new staffUI();
    }

    private static void startAllManager(){
        //something.getInstance()
    }

    private static void closeAllManager(){
        //something.close() -> to serialize all the objects
    }

}