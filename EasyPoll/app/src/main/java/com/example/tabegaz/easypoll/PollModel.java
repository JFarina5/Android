package com.example.tabegaz.easypoll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;


public class PollModel extends SQLiteOpenHelper {
    private ArrayList<PollModel> polllist = new ArrayList<PollModel>();
    private SQLiteDatabase db = this.getWritableDatabase();
    private  static final String DATABASE = "EasyPoll.db";
    private String firstName, lastName, state, party, username, password;
    private int electionYear;

    @Override
    public String toString() {
        return "PollModel{" +
                "username='" + username + '\'' +
                "password='" + password + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", party='" + party + '\'' +
                ", electionYear=" + electionYear +
                '}';
    }

    public class DataProvider implements Serializable {
        private String firstName, lastName, state, party;
        private int electionYear;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getParty() {
            return party;
        }

        public void setParty(String party) {
            this.party = party;
        }

        public int getElectionYear() {
            return electionYear;
        }

        public void setElectionYear(int electionYear) {
            this.electionYear = electionYear;
        }

        public DataProvider(String firstName, String lastName, String state, String party,
                            int electionYear) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.state = state;
            this.party = party;
            this.electionYear = electionYear;
        }
    }

    public PollModel(Context context) {
        super(context, DATABASE, null, 1
        );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE POLL (CANDIDATEID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, PARTY TEXT, STATE TEXT, ELECTIONYEAR INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE USER (USERNAME TEXT, PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from USER where username=?",
        new String[]{username});
        if (cursor.getCount() > 0)
            return false;
        else return true;
    }

    public boolean usernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from USER where username =? and password=?",
                new String[]{username, password});
        if(cursor.getCount()>0) return true;
        else return false;
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

    public boolean insertUserData(String username, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("PASSWORD", password);
        long result  = db.insert("USER", null, contentValues );
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
    public ArrayList<String> getPollList(){

        ArrayList<String> polllist = new ArrayList<String>();

        Cursor resultSet = db.rawQuery("Select FIRSTNAME, LASTNAME, STATE, PARTY,ELECTIONYEAR FROM POLL", null);
        if(resultSet.moveToFirst())
        {
            do {
                this.firstName = resultSet.getString(resultSet.getColumnIndex("FIRSTNAME"));
                this.lastName = resultSet.getString(resultSet.getColumnIndex("LASTNAME"));
                this.state = resultSet.getString(resultSet.getColumnIndex("STATE"));
                this.party = resultSet.getString(resultSet.getColumnIndex("PARTY"));
                this.electionYear = resultSet.getInt(resultSet.getColumnIndex("ELECTIONYEAR"));
                polllist.add(this.firstName);
                polllist.add(this.lastName);
                polllist.add(this.state);
                polllist.add(this.party);
                polllist.add(String.valueOf(this.electionYear));
            }
            while (resultSet.moveToNext()) ;
            resultSet.close();
        }

        return polllist;

    }


}
