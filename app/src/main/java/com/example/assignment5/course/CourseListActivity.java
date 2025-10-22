package com.example.assignment5.course;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.MainActivity;
import com.example.assignment5.R;
import com.example.assignment5.database.DBHelper;
import com.example.assignment5.model.Course;

import java.util.ArrayList;

public class CourseListActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayList<Course> courseList;
    ArrayAdapter<Course> adapter;
    ListView listView;
    Button btnAddCourse;
    int facultyId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listViewCourses);
        btnAddCourse = findViewById(R.id.btnAddCourse);

        // If opened from a specific faculty
        facultyId = getIntent().getIntExtra("faculty_id", -1);

        loadCourses();

        btnAddCourse.setOnClickListener(v -> {
            Intent i = new Intent(this, CourseCreateActivity.class);
            if (facultyId != -1)
                i.putExtra("faculty_id", facultyId);
            startActivity(i);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Course c = courseList.get(position);
            Intent intent = new Intent(this, CourseDetailActivity.class);
            intent.putExtra("course_id", c.getId());
            startActivity(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Course c = courseList.get(position);
            showOptionsDialog(c);
            return true;
        });
        Button btnBackMain = findViewById(R.id.btnBackMain);
        btnBackMain.setOnClickListener(v ->
                startActivity(new Intent(CourseListActivity.this, MainActivity.class))
        );
    }

    private void loadCourses() {
        Cursor cursor;
        if (facultyId != -1)
            cursor = dbHelper.getCoursesByFaculty(facultyId);
        else
            cursor = dbHelper.getAllCourses();

        courseList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Course c = new Course(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                );
                courseList.add(c);
            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        listView.setAdapter(adapter);
    }

    private void showOptionsDialog(Course course) {
        String[] options = {"Edit", "Delete", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(course.getCourseName());
        builder.setItems(options, (DialogInterface dialog, int which) -> {
            switch (which) {
                case 0:
                    Intent i = new Intent(this, CourseEditActivity.class);
                    i.putExtra("course_id", course.getId());
                    startActivity(i);
                    break;
                case 1:
                    dbHelper.deleteCourse(course.getId());
                    Toast.makeText(this, "Course deleted", Toast.LENGTH_SHORT).show();
                    loadCourses();
                    break;
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCourses();
    }
}

