package com.example.jana.myrecipes.models;

/**
 * Created by Kosara on 12/19/2017.
 */

public class RecModel {

    private int mREC_ID;
    private String mNAME;
    private String mTIME;
    private String mINGREDIENTS;
    private String mDescription;

    public RecModel(){}

    public void setID(int id){ this.mREC_ID = id; }
    public int getREC_ID(){ return this.mREC_ID; }

    public void setNAME(String name) { this.mNAME = name; }
    public String getNAME() {return this.mNAME; }

    public void setTIME(String time) { this.mTIME = time; }
    public String getTIME() {return this.mTIME; }

    public void setINGREDIENTS(String password){this.mINGREDIENTS = password; }
    public String getINGREDIENTS() { return this.mINGREDIENTS; }

    public void setDESCRIPTION(String description){this.mDescription = description; }
    public String getDESCRIPTION() { return this.mDescription; }

}
