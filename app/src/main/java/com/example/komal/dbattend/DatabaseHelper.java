package com.example.komal.dbattend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by komal on 16/2/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBName = "Records";
    private static final String teacherCourseTable = "TeacherCourses";
    private static final String  studentCourseTable = "StudentCourses";
    private static final String attendanceTable = "Attendance";
    String columnCourseName = "courseName";
    String columnCourseID = "courseID";
    String columnCourseType = "courseType";
    String columnDivID = "divisionID";

    private static final int DBVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + teacherCourseTable +
                " (" + columnCourseID + " VARCHAR(16), "
                + columnCourseName + " VARCHAR(32), "
                + columnCourseType + " VARCHAR(32), "
                + columnDivID + " INT, PRIMARY KEY (" +columnCourseID + "));";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Persons";
        db.execSQL(sql);
        onCreate(db);
    }


    public boolean insertData(String id, String name, String type, int div){
        String sql = "INSERT INTO " + teacherCourseTable + " VALUES " +
                "( '" + id + "', '" + name + "', '" + type + "', '" + div + "')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        return true;
    }
    public Cursor showData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " +teacherCourseTable + "";
        Cursor c = db.rawQuery(sql, null);
        return c;
     }
}

