package com.example.jana.myrecipes.models;


/**
 * Created by Kosara on 12/19/2017.
 */

public class UserModel {

    //    Username , Password,  e-mail, Name, Surname

    private int mUSER_ID;
    private String mUSERNAME;
    private String mPASSWORD;
    private String mEMAIL;
    private String mNAME;
    private String mSURNAME;

    public UserModel(){}

    public void setID(int id){ this.mUSER_ID = id; }
    public int getUSER_ID(){ return this.mUSER_ID; }

    public void setUSERNAME(String username) { this.mUSERNAME = username; }
    public String getUSERNAME() {return this.mUSERNAME; }

    public void setPASSWORD(String password){this.mPASSWORD = password; }
    public String getPASSWORD() { return this.mPASSWORD; }

    public void setEMAIL(String email){this.mEMAIL = email; }
    public String getEMAIL() { return this.mEMAIL; }

    public void setNAME(String name) { this.mNAME = name; }
    public String getNAME() {return this.mNAME; }

    public void setSURNAME(String surname) { this.mSURNAME = surname; }
    public String getSURNAME() {return this.mSURNAME; }
}
