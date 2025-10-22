package com.example.assignment5.course;



import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.R;
import com.example.assignment5.database.DBHelper;

public class CourseDetailActivity extends AppCompatActivity {

    TextView tvCourseName, tvCourseCode, tvFaculty;
    DBHelper dbHelper;
    int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        tvCourseName = findViewById(R.id.tvCourseName);
        tvCourseCode = findViewById(R.id.tvCourseCode);
        tvFaculty = findViewById(R.id.tvCourseFaculty);

        dbHelper = new DBHelper(this);
        courseId = getIntent().getIntExtra("course_id", -1);

        loadCourseDetails();
    }

    private void loadCourseDetails() {
        Cursor c = dbHelper.getCoursesByFaculty(tvFaculty.getId() );
        while (c.moveToNext()) {
            if (c.getInt(0) == courseId) {
                tvCourseName.setText("Course: " + c.getString(1));
                tvCourseCode.setText("Code: " + c.getString(2));
                tvFaculty.setText("Faculty: " + c.getString(4));
                break;
            }
        }
    }
}

