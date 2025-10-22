package com.example.assignment5;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.course.CourseCreateActivity;
import com.example.assignment5.faculty.FacultyCreateActivity;
import com.example.assignment5.faculty.FacultyListActivity;
import com.example.assignment5.student.StudentCreateActivity;
import com.example.assignment5.student.StudentListActivity;
import com.example.assignment5.course.CourseListActivity;

public class MainActivity extends AppCompatActivity {

    Button btnFaculties, btnStudents, btnCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFaculties = findViewById(R.id.btnFaculties);
        btnStudents = findViewById(R.id.btnStudents);
        btnCourses = findViewById(R.id.btnCourses);

        btnFaculties.setOnClickListener(v ->
                startActivity(new Intent(this, FacultyCreateActivity.class))
        );
        btnStudents.setOnClickListener(v ->
                startActivity(new Intent(this, StudentCreateActivity.class))
        );
        btnCourses.setOnClickListener(v ->
                startActivity(new Intent(this, CourseCreateActivity.class))
        );
    }
}

