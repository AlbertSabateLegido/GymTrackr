package com.gymtrackr.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gymtrackr.Throwables.InsertErrorThrowable;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "gymtrackr.db";
    private static int DATABASE_VERSION = 5;

    private static String ROUTINE_TABLE_NAME = "Routines";
    private static String ROUTINE_COLUMN_NAME = "name";
    //Muscle Group
    private static String ROUTINE_COLUMN_DAY_OF_THE_WEEK = "dayOfTheWeek";
    private static String CREATE_ROUTINE_TABLE = "CREATE TABLE " + ROUTINE_TABLE_NAME + "(" +
            ROUTINE_COLUMN_NAME            + " TEXT," +
            ROUTINE_COLUMN_DAY_OF_THE_WEEK + " TEXT," +
            "PRIMARY KEY(" + ROUTINE_COLUMN_NAME + "))";

    private static String EXERCISE_TABLE_NAME = "Exercises";
    private static String EXERCISE_COLUMN_NAME = "name";
    //Muscles Involved = muscle group ?
    private static String EXERCISE_COLUMN_REPETITIONS = "repetitions";
    private static String EXERCISE_COLUMN_SERIES = "series";
    private static String CREATE_EXERCISE_TABLE = "CREATE TABLE " + EXERCISE_TABLE_NAME + "(" +
            EXERCISE_COLUMN_NAME + " TEXT," +
            EXERCISE_COLUMN_REPETITIONS + " INTEGER," +
            EXERCISE_COLUMN_SERIES + " INTEGER," +
            "PRIMARY KEY(" + EXERCISE_COLUMN_NAME + "))";

    //Join Routines Exercises
    private static String JRE_TABLE_NAME = "JoinRoutinesExercises";
    private static String JRE_COLUMN_ROUTINE_NAME = "routineName";
    private static String JRE_COLUMN_EXERCISE_NAME = "exerciseName";
    private static String CREATE_JRE_TABLE = "CREATE TABLE " + JRE_TABLE_NAME + "(" +
            JRE_COLUMN_ROUTINE_NAME + " TEXT," +
            JRE_COLUMN_EXERCISE_NAME + " TEXT," +
            "PRIMARY KEY(" + JRE_COLUMN_ROUTINE_NAME + "," + JRE_COLUMN_EXERCISE_NAME + ")," +
            "FOREIGN KEY(" + JRE_COLUMN_ROUTINE_NAME + ") REFERENCES " + ROUTINE_TABLE_NAME + "(" + ROUTINE_COLUMN_NAME + ")," +
            "FOREIGN KEY(" + JRE_COLUMN_EXERCISE_NAME + ") REFERENCES " + EXERCISE_TABLE_NAME + "(" + EXERCISE_COLUMN_NAME + "))";

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ROUTINE_TABLE);
        sqLiteDatabase.execSQL(CREATE_EXERCISE_TABLE);
        sqLiteDatabase.execSQL(CREATE_JRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ROUTINE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE_NAME);
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

    public List<List<String>> getRoutines() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(ROUTINE_TABLE_NAME,null,null,
                null,null,null,null);

        List<List<String>> routinesList = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();
            do {
                List<String> routine = new ArrayList<>();
                for(int i = 0; i < cursor.getColumnCount(); ++i) {
                    routine.add(cursor.getString(i));
                }
                routinesList.add(routine);
            } while (cursor.moveToNext());
        }

        return routinesList;
    }

    public void putExercise(String name, int repetitions, int series) throws InsertErrorThrowable {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_COLUMN_NAME,name);
        contentValues.put(EXERCISE_COLUMN_REPETITIONS,repetitions);
        contentValues.put(EXERCISE_COLUMN_SERIES,series);
        long result = sqLiteDatabase.insert(EXERCISE_TABLE_NAME,null,contentValues);

        if(result == -1) throw new InsertErrorThrowable(EXERCISE_TABLE_NAME);

        return;
    }

    public void putJRE(String routineName,String exerciceName) throws InsertErrorThrowable {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(JRE_COLUMN_ROUTINE_NAME,routineName);
        contentValues.put(JRE_COLUMN_EXERCISE_NAME,exerciceName);
        long result = sqLiteDatabase.insert(JRE_TABLE_NAME,null,contentValues);

        if(result == -1) throw new InsertErrorThrowable(JRE_TABLE_NAME);

        return;
    }
}
