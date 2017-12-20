package com.example.jana.myrecipes.models;

/**
 * Created by Kosara on 12/19/2017.
 */

public class UserRecipeModel {
    private String mUSERNAME;
    private int mREC_ID;

    public UserRecipeModel(){}

    public void setUSERNAME(String username) { this.mUSERNAME = username; }
    public String getUSERNAME() {return this.mUSERNAME; }

    public void setID(int id){ this.mREC_ID = id; }
    public int getREC_ID(){ return this.mREC_ID; }
}
