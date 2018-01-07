package de.uni_stuttgart.sopra_ws1617_team8.tong.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class for the handle of the Main Database
 */
public class DatabaseAdapter {

    //Datenbank Namen und Versionen
    private static final String Database = "tong";
    private static final int DATABASE_VERSION = 2; // Remember when update the Database please Update the Version Number also
    private static final String DB_ON_TNAME = "Personen";
    private static final String DB_TW_TNAME = "Audio";

    //Keys - Person
    private static final String P_ID = "_pid";
    private static final String P_TITLE = "_anrede";
    private static final String P_GIVENNAME = "_vorname";
    private static final String P_SURNAME = "_nachname";
    private static final String P_REGION = "_region";
    private static final String P_COMPANY = "_firma";

    //Keys - Audio
    private static final String A_ID = "_aid";
    private static final String A_PID = "_pid";
    private static final String A_FILENAME = "_file";
    private static final String A_RATING = "_rating";

    //All Keys
    private static final String[] T_ONE_ALL_KEYS = {P_ID, P_TITLE, P_GIVENNAME, P_SURNAME, P_REGION, P_COMPANY};
    private static final String[] T_TWO_ALL_KEYS = {A_ID, A_PID, A_FILENAME, A_RATING};

    //Spalten (Spalte 0 - Autoincrement id)
    public static final int COL_P_ID = 0;
    public static final int COL_P_TITLE = 1;
    public static final int COL_P_GIVEN = 2;
    public static final int COL_P_SUR = 3;
    public static final int COL_P_REG = 4;
    public static final int COL_P_COM = 5;
    public static final int COL_A_FILE = 2;
    public static final int COL_A_RATING = 3;

    //CREATE Strings
    private static final String CREATE_FIRST_DB =
            "CREATE TABLE IF NOT EXISTS " + DB_ON_TNAME + "(" +
                    P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    P_TITLE + " VARCHAR NOT NULL, " +
                    P_GIVENNAME + " VARCHAR NOT NULL, " +
                    P_SURNAME + " VARCHAR NOT NULL, " +
                    P_REGION + " VARCHAR NOT NULL, " +
                    P_COMPANY + " INTEGER NOT NULL);";

    private static final String CREATE_SECOND_DB =
            "CREATE TABLE IF NOT EXISTS " + DB_TW_TNAME + "(" +
                    A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    A_PID + " INTEGER NOT NULL, " +
                    A_FILENAME + " VARCHAR NOT NULL, " +
                    A_RATING + " FLOAT NOT NULL);";

    //Cursor and Construcors
    private final Context context;
    private DB_Helper myDBHelper;
    private SQLiteDatabase db;

    // Methoden DB 1

    /**
     * Method to insert Data into the Person DatabaseAdapter
     *
     * @param title     The title of the Person (e.g. Mr., Mrs.)
     * @param givenName The given Name of the Person
     * @param surName   The surname of the Person
     * @param region    The region where the Person come from
     * @param company   The company of the Person
     * @return -1 if it hasn't insert any Data
     */
    public long insertDBOne(String title, String givenName, String surName, String region, String company) {

        ContentValues values = new ContentValues();
        values.put(P_TITLE, title);
        values.put(P_GIVENNAME, givenName);
        values.put(P_SURNAME, surName);
        values.put(P_REGION, region);
        values.put(P_COMPANY, company);

        return db.insert(DB_ON_TNAME, null, values);
    }

    /**
     * Method to get all the Data from the Person DatabaseAdapter
     *
     * @return a cursor which could point from line to line across the data
     */
    public Cursor getDBOneRows() {
        Cursor c = db.query(true, DB_ON_TNAME, T_ONE_ALL_KEYS, null, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Method to get the Person ID from the Person Database
     *
     * @param whereClause actual to less input to specify the person - later we will search
     *                    for surname, givenname, region and company to get a single person
     * @return the cursor right on the point
     */
    public Cursor getDBOnePersonID(String whereClause) {
        String where = P_SURNAME + "='" + whereClause + "'";
        Cursor c = db.query(true, DB_ON_TNAME, T_ONE_ALL_KEYS, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Method to get the Person ID from the Person Database
     *
     * @param region  a
     * @param company a
     * @param sort    a
     * @return the cursor right on the point
     */
    public Cursor getDBSearchRows(int sort, String region, String company) {
        String where = null;
        String orderBy = null;
        if (sort == 0) {
            if (region.isEmpty() && company.isEmpty()) {
                where = null;
            } else if (region.isEmpty() && !company.isEmpty()) {
                where = P_COMPANY + "='" + company + "'";
            } else if (!region.isEmpty() && company.isEmpty()) {
                where = P_REGION + "='" + region + "'";
            } else if (!region.isEmpty() && !company.isEmpty()) {
                where = P_REGION + "='" + region + "' AND " + P_COMPANY + "='" + company + "'";
            }
        } else if (sort == 1) {
            if (region.isEmpty() && company.isEmpty()) {
                where = null;
                orderBy = P_SURNAME + " DESC";
            } else if (region.isEmpty() && !company.isEmpty()) {
                where = P_COMPANY + "='" + company + "'";
                orderBy = P_SURNAME + " DESC";
            } else if (!region.isEmpty() && company.isEmpty()) {
                where = P_REGION + "='" + region + "'";
                orderBy = P_SURNAME + " DESC";
            } else if (!region.isEmpty() && !company.isEmpty()) {
                where = P_REGION + "='" + region + "' AND " + P_COMPANY + "='" + company + "'";
                orderBy = P_SURNAME + " DESC";
            }
        } else {
            if (region.isEmpty() && company.isEmpty()) {
                where = null;
                orderBy = P_GIVENNAME + " DESC";
            } else if (region.isEmpty() && !company.isEmpty()) {
                where = P_COMPANY + "='" + company + "'";
                orderBy = P_GIVENNAME + " DESC";
            } else if (!region.isEmpty() && company.isEmpty()) {
                where = P_REGION + "='" + region + "'";
                orderBy = P_GIVENNAME + " DESC";
            } else if (!region.isEmpty() && !company.isEmpty()) {
                where = P_REGION + "='" + region + "' AND " + P_COMPANY + "='" + company + "'";
                orderBy = P_GIVENNAME + " DESC";
            }
        }

        Cursor c = db.query(true, DB_ON_TNAME, T_ONE_ALL_KEYS, where, null, null, null, null, orderBy);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Method to get one Single Person
     *
     * @param whereClause the PersonID from the Person
     * @return a single Persons Data
     */
    public Cursor getDBOnePerson(int whereClause) {
        String where = P_ID + "='" + whereClause + "'";
        Cursor c = db.query(true, DB_ON_TNAME, T_ONE_ALL_KEYS, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Method to get a List of Persons
     *
     * @param whereClause The Searched Word
     * @return Cursor which contains the List
     */
    public Cursor getDBOneFilteredList(String whereClause) {
        String where;
        Cursor c;
        if (whereClause.isEmpty()){
            where = null;
            c = db.query(true, DB_ON_TNAME, T_ONE_ALL_KEYS, where, null, null, null, null, null);
        } else {
            where = P_GIVENNAME + " LIKE '%" + whereClause + "%' OR "
                    + P_SURNAME + " LIKE '%" + whereClause + "%' OR "
                    + P_COMPANY + " LIKE '%" + whereClause + "%' OR "
                    + P_REGION + " LIKE '%" + whereClause + "%'";
            c = db.query(true, DB_ON_TNAME, T_ONE_ALL_KEYS, where, null, null, null, null, null);
        }
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public String getDatabasePath(){
        return context.getDatabasePath(Database).getAbsolutePath().toString();
    }


    /**
     * Method to delete a Specific Row in the Person Database
     *
     * @param PID the Person ID
     * @return true if its deleted, false if not
     */
    public boolean deleteDBOnePerson(String PID) {
        String where = P_ID + "='" + PID + "'";
        return db.delete(DB_ON_TNAME, where, null) != 0;
    }

    /**
     * Method to Update the given name
     *
     * @param PID       The Person ID
     * @param GIVENNAME The new given name
     * @return true if the update was correct, false if not
     */
    public boolean updateGivenName(int PID, String GIVENNAME) {
        String where = P_ID + "='" + PID + "'";

        ContentValues newValues = new ContentValues();
        newValues.put(P_GIVENNAME, GIVENNAME);

        // Insert it into the database.
        return db.update(DB_ON_TNAME, newValues, where, null) != 0;
    }


    /**
     * Method to Update the surname
     *
     * @param PID     The Person ID
     * @param SURNAME The new surname
     * @return true if the update was correct, false if not
     */
    public boolean updateSurName(int PID, String SURNAME) {
        String where = P_ID + "='" + PID + "'";

        ContentValues newValues = new ContentValues();
        newValues.put(P_SURNAME, SURNAME);

        // Insert it into the database.
        return db.update(DB_ON_TNAME, newValues, where, null) != 0;
    }


    /**
     * Method to Update the title
     *
     * @param PID   The Person ID
     * @param TITLE The new title of the Person
     * @return true if the update was correct, false if not
     */
    public boolean updateTitle(int PID, String TITLE) {
        String where = P_ID + "='" + PID + "'";

        ContentValues newValues = new ContentValues();
        newValues.put(P_TITLE, TITLE);

        // Insert it into the database.
        return db.update(DB_ON_TNAME, newValues, where, null) != 0;
    }


    /**
     * Method to Update the region
     *
     * @param PID    The Person ID
     * @param REGION The new region
     * @return true if the update was correct, false if not
     */
    public boolean updateRegion(int PID, String REGION) {
        String where = P_ID + "='" + PID + "'";

        ContentValues newValues = new ContentValues();
        newValues.put(P_REGION, REGION);

        // Insert it into the database.
        return db.update(DB_ON_TNAME, newValues, where, null) != 0;
    }


    /**
     * Method to Update the company
     *
     * @param PID     The Person ID
     * @param COMPANY The new company
     * @return true if the update was correct, false if not
     */
    public boolean updateCompany(int PID, String COMPANY) {
        String where = P_ID + "='" + PID + "'";

        ContentValues newValues = new ContentValues();
        newValues.put(P_COMPANY, COMPANY);

        // Insert it into the database.
        return db.update(DB_ON_TNAME, newValues, where, null) != 0;
    }

    // Methods DB 2

    /**
     * Method to insert Data into the Audio DatabaseAdapter
     *
     * @param personID The Person ID for the specific Audio file / Person
     * @param fileName The Filename where the file is saved
     * @return -1 if it hasn't insert any Data
     */
    public long insertDBTwo(int personID, String fileName, float rating) {
        ContentValues values = new ContentValues();
        values.put(A_PID, personID);
        values.put(A_FILENAME, fileName);
        values.put(A_RATING, rating);

        return db.insert(DB_TW_TNAME, null, values);
    }


    /**
     * Method to get the specific filePath from the Audio DatabaseAdapter
     *
     * @return a cursor which could point from line to line across the data
     */
    public Cursor getDBTwoSpecificPath(String PID) {
        String where = A_PID + "='" + PID + "'";
        Cursor c = db.query(true, DB_TW_TNAME, T_TWO_ALL_KEYS, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Method to update the Rating on the specific Contact Card
     *
     * @param p_id   the PersonID which should be changed
     * @param rating the new Rating of the Audiofile
     * @return true if the input was correct, false if nothing had added to the
     * database
     */
    public boolean updateRating(int p_id, float rating) {
        String where = A_ID + "='" + p_id + "'";

        ContentValues newValues = new ContentValues();
        newValues.put(A_RATING, rating);

        // Insert it into the database.
        return db.update(DB_TW_TNAME, newValues, where, null) != 0;
    }

    /**
     * Method to delete a Specific Row in the Audio Database
     *
     * @param PID the Person ID
     * @return true if its deleted, false if not
     */
    public boolean deleteDBTwoAudio(String PID) {
        String where = A_ID + "='" + PID + "'";
        return db.delete(DB_TW_TNAME, where, null) != 0;
    }

    /**
     * Method to get all the Data from the Person DatabaseAdapter
     *
     * @return a cursor which could point from line to line across the data
     */
    public Cursor getDBTwoRows() {
        Cursor c = db.query(true, DB_TW_TNAME, T_TWO_ALL_KEYS, null, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    /**
     * Constructor of the Database class
     *
     * @param ctx the context of the actual activity
     */
    public DatabaseAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DB_Helper(context);
    }

    /**
     * Method to open the Database stream
     *
     * @return the Database context
     */
    public DatabaseAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    /**
     * Method to close the Database stream
     */
    public void close() {
        myDBHelper.close();
    }

    /**
     * Helper Class for the SQLite.
     * <p>
     * This class Creates and Kills all the Database table if we need one.
     */
    private class DB_Helper extends SQLiteOpenHelper {

        /**
         * Constructor of the Helper Class
         *
         * @param context v
         */
        private DB_Helper(Context context) {
            super(context, Database, null, DATABASE_VERSION);
        }

        /**
         * Method which Creates all the Databases / Tables
         *
         * @param db requires the database
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_FIRST_DB);
            db.execSQL(CREATE_SECOND_DB);
        }

        /**
         * Method which upgrades the Databases / Tables
         *
         * @param db         the database which should be upgraded
         * @param oldVersion the old version of the database
         * @param newVersion the new version of the database
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_ON_TNAME);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TW_TNAME);
            onCreate(db);
        }
    }
}
