package reportpkg;

import camppkg.*;

public abstract class ReportFilterCamp {
    
    private String filename;
    private String ID;

    public ReportFilterCamp(String ID, String filename) {
        this.filename = filename;
        this.ID = ID;
    }

    public String getFileName() {
        return filename;
    }

    public String getID() {
        return ID;

    }

    public abstract void write();
    
}
