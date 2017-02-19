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
    String columnTCourseName = "courseName";
    String columnTCourseID = "courseID";
    String columnTCourseType = "courseType";
    String columnTDivID = "divisionID";
    String columnSCourseID = "s_courseID";
    String columnStudentID = "studentID";
    String columnStatus = "status";
    String columnDateTime = "datetime";


    private static final int DBVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTeacherCourses = "CREATE TABLE " + teacherCourseTable +
                " (" + columnTCourseID + " VARCHAR(16), "
                + columnTCourseName + " VARCHAR(32), "
                + columnTCourseType + " VARCHAR(32), "
                + columnTDivID + " INT, PRIMARY KEY (" + columnTCourseID + "));";
        String createStudentCourses = "CREATE TABLE " + studentCourseTable +
                " (" + columnSCourseID + " VARCHAR(16), "
                + columnStudentID + " VARCHAR(16), PRIMARY KEY (" + columnSCourseID + ", " + columnStudentID + ")," +
                " FOREIGN KEY ( "+ columnSCourseID +") REFERENCES " + teacherCourseTable + " ( " + columnTCourseID +") " +
                "ON DELETE CASCADE);";
        String createAttendance = "CREATE TABLE " + attendanceTable +
                " (" + columnTCourseID + " VARCHAR(16), "
                + columnStudentID + " VARCHAR(16), "
                + columnStatus + " VARCHAR(4), "
                + columnDateTime + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                "PRIMARY KEY (" + columnTCourseID + ", " + columnStudentID + ", " + columnStatus + ", " + columnDateTime + "));";
        try {
            db.execSQL(createTeacherCourses);
            db.execSQL(createStudentCourses);
            db.execSQL(createAttendance);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS '" + teacherCourseTable +"'";
        String sql1 = "DROP TABLE IF EXISTS '" + studentCourseTable +"'";
        String sql2 = "DROP TABLE IF EXISTS '" + attendanceTable +"'";
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        onCreate(db);
    }

    public boolean insertTeacherCourse(MainActivity.TeacherCourse tc){
        String sql = "INSERT INTO " + teacherCourseTable + " VALUES " +
                "( '" + tc.ID + "', '" + tc.name + "', '" + tc.type + "', '" + tc.div + "')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        return true;
    }

    public boolean insertStudentCourse(MainActivity.StudentCourse sc){
        String sql = "INSERT INTO " + studentCourseTable + " VALUES " +
                "( '" + sc.studentID + "', '" + sc.courseID + "')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        return true;
    }

    public boolean insertAttendance(MainActivity.Attendance at){
        String sql = "INSERT INTO " + attendanceTable + "( " + columnTCourseID +", " + columnStudentID + ", "+ columnStatus +") VALUES " +
                "( '" + at.courseID + "', '" + at.studentID + "', '" + at.status + "')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        return true;
    }

    public Cursor getTeacherCoursesData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " +teacherCourseTable + "";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getStudentCourses(String sid){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " +studentCourseTable +" WHERE " + columnStudentID+"="+sid+"";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }



}

