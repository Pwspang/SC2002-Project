package camppkg;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Camp mycamp = new Camp("CAMP1234", new CampInformation());
        
        CampInformation a = mycamp.getCampInfo();
        System.out.println(a.getSlotsSummary());
    }
}
