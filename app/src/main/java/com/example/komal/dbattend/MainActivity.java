package com.example.komal.dbattend;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView text1, text2;
    String result;
    int i;
    Cursor c;
    StringBuilder stringBuilder = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        db = new DatabaseHelper(this);

        //testing start
        TeacherCourse fcp = new TeacherCourse("65", "FCP", "programming", 2);
        TeacherCourse cpp = new TeacherCourse("34", "BEE", "electrical", 1);
        StudentCourse s1 = new StudentCourse("1232", "34");
        StudentCourse s2 = new StudentCourse("1232", "65");
        Attendance a1 = new Attendance("1232", "34", "P");
        db.insertTeacherCourse(fcp);
        db.insertTeacherCourse(cpp);
        db.insertStudentCourse(s1);
        db.insertStudentCourse(s2);
        db.insertAttendance(a1);
        c = db.getTeacherCoursesData();
        while(c != null && c.moveToNext()){
            i = c.getColumnIndex("courseName");
            stringBuilder.append(c.getString(i));
            i = c.getColumnIndex("courseID");
            stringBuilder.append(c.getString(i));
            i = c.getColumnIndex("courseType");
            stringBuilder.append(c.getString(i));
            i = c.getColumnIndex("divisionID");
            stringBuilder.append(c.getString(i));
            String name = stringBuilder.toString();
            text1.setText(name);
        }
        c = db.getStudentCourses("1232");
        if(c != null && c.moveToNext()){
            i = c.getColumnIndex("s_courseID");
            String cid = c.getString(i);
            text2.setText(cid);
        }else{
            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }
        //testing end

    }
    class TeacherCourse{
        String name, ID, type;
        int div;
        TeacherCourse(String id, String name, String type, int div){
            this.name = name;
            this.div = div;
            this.ID = id;
            this.type = type;
        }
    }
    class StudentCourse{
        String studentID, courseID;
        StudentCourse(String sid, String cid){
            this.studentID = sid;
            this.courseID = cid;
        }
    }
    class Attendance{
        String studentID, courseID, status;
        Attendance(String sid, String cid, String stat){
            this.studentID = sid;
            this.courseID = cid;
            this.status = stat;
        }
    }


}
