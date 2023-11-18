package reportpkg;

import camppkg.*;

public abstract class ReportFilterCamp {
    private String filename;
    private String campID;

    public ReportFilterCamp(String campID, String filename) {
        this.filename = filename;
        this.campID = campID;
    }

    public String getFileName() {
        return filename;
    }

    public String getCampID() {
        return campID;

    }

    public abstract void write();

}
