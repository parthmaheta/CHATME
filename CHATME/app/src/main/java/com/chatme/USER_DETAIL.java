package com.chatme;

public class USER_DETAIL {
    private int ID;
    private String NAME;
    private String EMAIL;
    private String PASSWORD;
    private String PICTURE;
    private String STATUS;
    private String LAST_SEEN;

    public USER_DETAIL() {
    }

    public USER_DETAIL(int ID, String NAME, String EMAIL, String PASSWORD, String PICTURE, String STATUS, String SEEN) {
        this.ID = ID;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.PICTURE = PICTURE;
        this.STATUS = STATUS;

        this.LAST_SEEN = SEEN;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPICTURE() {
        return PICTURE;
    }

    public void setPICTURE(String PICTURE) {
        this.PICTURE = PICTURE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getLASTSEEN() {
        return LAST_SEEN;
    }

    public void setSEEN(String SEEN) {
        this.LAST_SEEN = SEEN;
    }

}
