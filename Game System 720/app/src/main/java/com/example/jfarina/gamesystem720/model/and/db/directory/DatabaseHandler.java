package com.example.jfarina.gamesystem720.model.and.db.directory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Name
    private static final String DATABASE_NAME = "ePillDB";
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Table Names
    private static final String TABLE_USER = "user";
    //  create  Tables for user

    private static final String CREATE_TABLE_USER = "CREATE TABLE "  + TABLE_USER+
            "(USERID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, " +
            "EMAIL TEXT, DATEOFBIRTH TEXT, USERNAME TEXT, PASSWORD TEXT)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating required tables
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // create new tables
        onCreate(sqLiteDatabase);
    }

    // ------------------------ "USER" table methods ----------------//

    public String getSecurePassword(String passwordToHash, String messageSalt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(messageSalt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        int count=0;
        String whereclause = "USERNAME=?";
        String[] whereargs = new String[]{username};
        Cursor csr =db.query("USER",null,whereclause,whereargs,null,null,null);
        count = csr.getCount();
        csr.close();
        return count < 1;
    }

    public boolean usernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from USER where username =? and password=?",
                new String[]{username, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public boolean insertUser(UserModel userModel)
    {
        String password;
        password = getSecurePassword(userModel.getPassword(),    "Easy Pill");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FIRSTNAME", userModel.getFirstName());
        values.put("LASTNAME", userModel.getLastName());
        values.put("DATEOFBIRTH",userModel.getDateOfBirth());
        values.put("USERNAME", userModel.getUsername());
        values.put("EMAIL", userModel.getEmail());
        values.put("PASSWORD", password);
        long result  = db.insert(TABLE_USER, null, values );
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertUserData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("PASSWORD", password);
        long result  = db.insert("USER", null, contentValues );
        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean getLoginInfo(UserModel user){
        SQLiteDatabase db = this.getReadableDatabase();
        String password;
        password= getSecurePassword(user.getPassword(),    "Easy Pill");
        String query = "Select USERNAME, PASSWORD FROM " + TABLE_USER + " WHERE USERNAME = '"+user.getUsername() +"' AND PASSWORD= '"+password+"'";
        Cursor resultSet = db.rawQuery(query, null);
        if(resultSet.getCount()== 0)
            return false;
        else
            return true;
        //resultSet.close();
    }
}

