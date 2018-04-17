package com.example.jfarina.easypill.model.and.db.directory;

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
    private static final int DATABASE_VERSION = 5;
    // Table Names
    private static final String TABLE_MEDICATION = "Medication";
    private static final String TABLE_USER = "user";
    //  create  Tables for medication and user
    String CREATE_TABLE_MEDICATION = "CREATE TABLE "  + TABLE_MEDICATION  +
            " (MEDICATIONID INTEGER PRIMARY KEY AUTOINCREMENT, MEDNAME TEXT, FREQUENCY TEXT, " +
            " NUMDAYS INTEGER, NOTES TEXT, DOSAGE TEXT, INITIALAMOUNT TEXT)";

    String CREATE_TABLE_USER = "CREATE TABLE "  + TABLE_USER+
            "(USERID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, " +
            "EMAIL TEXT, PASSWORD TEXT)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating required tables
        sqLiteDatabase.execSQL(CREATE_TABLE_MEDICATION);
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // create new tables
        onCreate(sqLiteDatabase);
    }

    // ------------------------ "MEDICATION" table methods ----------------//

    public Boolean insertMed(MedModel medModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("MEDNAME", medModel.getMedName());
        values.put("FREQUENCY", medModel.getFrequency());
        values.put("NOTES", medModel.getNotes());
        values.put("DOSAGE",medModel.getDosage());
        values.put("INITIALAMOUNT",medModel.getInitialAmount());
        values.put("NUMDAYS",medModel.getNumDays());

        // insert row
        long resultSet = db.insert(TABLE_MEDICATION, null, values);
        if(resultSet== -1)
            return false;
        else
            return true;
    }

    public MedModel getMed(long medicationID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MEDICATION + " WHERE  MEDICATIONID =" + medicationID;

        Cursor resultSet = db.rawQuery(selectQuery, null);

        if (resultSet != null)
            resultSet.moveToFirst();


        MedModel medModel = new MedModel();
        medModel.setMedName((resultSet.getString(resultSet.getColumnIndex("MEDNAME"))));
        medModel.setNumDays(resultSet.getInt(resultSet.getColumnIndex("FREQUENCY")));
        medModel.setNotes((resultSet.getString(resultSet.getColumnIndex("NOTES"))));
        medModel.setDosage((resultSet.getString(resultSet.getColumnIndex("DOSAGE"))));
        medModel.setInitialAmount((resultSet.getString(resultSet.getColumnIndex("INITIALAMOUNT"))));
        medModel.setNumDays(resultSet.getInt(resultSet.getColumnIndex("NUMDAYS")));
        return medModel;
    }

    public List<MedModel> getAllMedications() {
        List<MedModel> medList = new ArrayList<MedModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (resultSet.moveToFirst()) {
            do {
                MedModel medModel = new MedModel();
                medModel.setMedicationID((resultSet.getInt(resultSet.getColumnIndex("MEDICATIONID"))));
                medModel.setMedName((resultSet.getString(resultSet.getColumnIndex("MEDNAME"))));
                medModel.setFrequency((resultSet.getInt(resultSet.getColumnIndex("FREQUENCY"))));
                medModel.setNotes((resultSet.getString(resultSet.getColumnIndex("NOTES"))));
                medModel.setDosage((resultSet.getString(resultSet.getColumnIndex("DOSAGE"))));
                medModel.setInitialAmount((resultSet.getString(resultSet.getColumnIndex("INITALAMOUNT"))));
                medModel.setNumDays((resultSet.getInt(resultSet.getColumnIndex("NUMDAYS"))));

                // adding to poll list
                medList.add(medModel);
            } while (resultSet.moveToNext());
        }

        return medList;
    }

    public int updateMed(MedModel medModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("MEDNAME", medModel.getMedName());
        values.put("FREQUENCY", medModel.getFrequency());
        values.put("NOTES", medModel.getNotes());
        values.put("DOSAGE", medModel.getDosage());
        values.put("INITIALAMOUNT", medModel.getInitialAmount());
        values.put("NUMDAYS", medModel.getNumDays());
        // updating row
        return db.update(TABLE_MEDICATION, values, "MEDICATIONID" + " = ?",
                new String[] { String.valueOf(medModel.getMedicationId()) });
    }

    public void deleteMed(long medicationID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICATION, "MEDICATIONID" + " = ?",
                new String[] { String.valueOf(medicationID) });
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

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        int count=0;
        String whereclause = "EMAIL=?";
        String[] whereargs = new String[]{email};
        Cursor csr =db.query("USER",null,whereclause,whereargs,null,null,null);
        count = csr.getCount();
        csr.close();
        return count < 1;
    }

    public boolean usernamePassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from USER where email =? and password=?",
                new String[]{email, password});
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
        values.put("EMAIL", userModel.getEmail());
        values.put("AGE", userModel.getAge());
        values.put("PASSWORD", password);
        long result  = db.insert(TABLE_USER, null, values );
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertUserData(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
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
        String query = "Select EMAIL, PASSWORD FROM " + TABLE_USER + " WHERE EMAIL = '"+user.getEmail() +"' AND PASSWORD= '"+password+"'";
        Cursor resultSet = db.rawQuery(query, null);
        if(resultSet.getCount()== 0)
            return false;
        else
            return true;
        //resultSet.close();
    }
}

