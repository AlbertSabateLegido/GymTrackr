package com.gymtrackr.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gymtrackr.Throwables.InsertErrorThrowable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "gymtrackr.db";
    private static int DATABASE_VERSION = 3;

    private static String ROUTINE_TABLE_NAME = "Routines";
    private static String ROUTINE_COLUMN_NAME = "Name";
    //Muscle Group
    private static String ROUTINE_COLUMN_DAY_OF_THE_WEEK = "DayOfTheWeek";
    private static String CREATE_ROUTINE_TABLE = "CREATE TABLE " + ROUTINE_TABLE_NAME + "(" +
            ROUTINE_COLUMN_NAME            + " TEXT," +
            ROUTINE_COLUMN_DAY_OF_THE_WEEK + " TEXT," +
            "PRIMARY KEY(" + ROUTINE_COLUMN_NAME + "))";

    private static String EXERCICE_TABLE_NAME = "Exercices";
    private static String EXERCICE_COLUMN_NAME = "Name";
    //Muscles Involved = muscle group ?
    private static String EXERCICE_COLUMN_REPETITIONS = "Repetitions";
    private static String EXERCICE_COLUMN_SERIES = "Series";
    private static String CREATE_EXERCICE_TABLE = "CREATE TABLE " + EXERCICE_TABLE_NAME + "(" +
            EXERCICE_COLUMN_NAME        + " TEXT," +
            EXERCICE_COLUMN_REPETITIONS + " INTEGER," +
            EXERCICE_COLUMN_SERIES      + " INTEGER," +
            "PRIMARY KEY(" + EXERCICE_COLUMN_NAME + "))";

    //Join Routines Exercices
    private static String JRE_TABLE_NAME = "JoinRoutinesExercices";
    private static String JRE_ROUTINE_NAME = "RoutineName";
    private static String JRE_EXERCICE_NAME = "ExerciceName";
    private static String CREATE_JRE_TABLE = "CREATE TABLE " + JRE_TABLE_NAME + "(" +
            JRE_ROUTINE_NAME  + " TEXT," +
            JRE_EXERCICE_NAME + " TEXT," +
            "PRIMARY KEY(" + JRE_ROUTINE_NAME + "," + JRE_EXERCICE_NAME + ")," +
            "FOREIGN KEY(" + JRE_ROUTINE_NAME + ") REFERENCES " + ROUTINE_TABLE_NAME + "(" + ROUTINE_COLUMN_NAME + ")," +
            "FOREIGN KEY(" + JRE_EXERCICE_NAME + ") REFERENCES " + EXERCICE_TABLE_NAME + "(" + EXERCICE_COLUMN_NAME + "))";

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ROUTINE_TABLE);
        sqLiteDatabase.execSQL(CREATE_EXERCICE_TABLE);
        sqLiteDatabase.execSQL(CREATE_JRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ROUTINE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXERCICE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JRE_TABLE_NAME);
    }

    public void putRoutine(String name, String dayOfTheWeek) throws InsertErrorThrowable {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ROUTINE_COLUMN_NAME,name);
        contentValues.put(ROUTINE_COLUMN_DAY_OF_THE_WEEK,dayOfTheWeek);
        long result = sqLiteDatabase.insert(ROUTINE_TABLE_NAME,null,contentValues);

        if(result == -1) throw new InsertErrorThrowable(ROUTINE_TABLE_NAME);

        return;

    }

    public void putExercice(String name,int repetitions,int series) throws InsertErrorThrowable {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCICE_COLUMN_NAME,name);
        contentValues.put(EXERCICE_COLUMN_REPETITIONS,repetitions);
        contentValues.put(EXERCICE_COLUMN_SERIES,series);
        long result = sqLiteDatabase.insert(EXERCICE_TABLE_NAME,null,contentValues);

        if(result == -1) throw new InsertErrorThrowable(EXERCICE_TABLE_NAME);

        return;
    }

    public void putJRE(String routineName,String exerciceName) throws InsertErrorThrowable {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(JRE_ROUTINE_NAME,routineName);
        contentValues.put(JRE_EXERCICE_NAME,exerciceName);
        long result = sqLiteDatabase.insert(JRE_TABLE_NAME,null,contentValues);

        if(result == -1) throw new InsertErrorThrowable(JRE_TABLE_NAME);

        return;
    }
}
