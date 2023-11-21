package reportpkg;

public abstract class ReportFilterCamp {

    private String ID;

    public ReportFilterCamp(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;

    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public abstract void write();


}
