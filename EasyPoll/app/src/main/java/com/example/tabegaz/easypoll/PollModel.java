package com.example.tabegaz.easypoll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class PollModel extends SQLiteOpenHelper {
    private ArrayList<PollModel> polllist = new ArrayList<PollModel>();
    private SQLiteDatabase db = this.getWritableDatabase();
    private  static final String DATABASE = "EasyPoll.db";
    private String firstName, lastName, state, party;
    private int electionYear;

    @Override
    public String toString() {
        return "PollModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", party='" + party + '\'' +
                ", electionYear=" + electionYear +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getState() {
        return state;
    }

    public String getParty() {
        return party;
    }

    public int getElectionYear() {
        return electionYear;
    }

    public PollModel(Context context) {
        super(context, DATABASE, null, 1
        );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE POLL (CANDIDATEID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, PARTY TEXT, STATE TEXT, ELECTIONYEAR INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String firstname, String lastname, String party, String state, Integer electionYear)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME", firstname);
        contentValues.put("LASTNAME", lastname);
        contentValues.put("PARTY", party);
        contentValues.put("STATE", state);
        contentValues.put("ELECTIONYEAR", electionYear);
        long result  = db.insert("POLL", null, contentValues );
        if(result == -1)
            return false;
        else
            return true;

    }

    public ArrayList<String> getName(){

ArrayList<String> nameList = new ArrayList<String>();
        Cursor resultSet = db.rawQuery("Select FIRSTNAME, LASTNAME FROM POLL", null);
        if(resultSet.moveToFirst())
        {
            do {
                    this.firstName = resultSet.getString(resultSet.getColumnIndex("FIRSTNAME"));
                    this.lastName = resultSet.getString(resultSet.getColumnIndex("LASTNAME"));
                nameList.add(this.firstName + " " + this.lastName);
                    Log.d("FirstName", firstName);

                }
                while (resultSet.moveToNext()) ;
                resultSet.close();
            }

            return nameList;

    }
    public ArrayList<PollModel> getPollList(){


        Cursor resultSet = db.rawQuery("Select * FROM POLL", null);
        if(resultSet.moveToFirst())
        {
            do {
                this.firstName = resultSet.getString(resultSet.getColumnIndex("FIRSTNAME"));
                this.lastName = resultSet.getString(resultSet.getColumnIndex("LASTNAME"));
                this.state = resultSet.getString(resultSet.getColumnIndex("STATE"));
                this.party = resultSet.getString(resultSet.getColumnIndex("PARTY"));
                this.electionYear = resultSet.getInt(resultSet.getColumnIndex("ELECTIONYEAR"));
                polllist.add(this);

            }
            while (resultSet.moveToNext()) ;
            resultSet.close();
        }

        return polllist;

    }


}
