package com.example.assignment5.course;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.R;
import com.example.assignment5.database.DBHelper;
import com.example.assignment5.model.Faculty;

import java.util.ArrayList;

public class CourseCreateActivity extends AppCompatActivity {

    EditText etCourseName, etCourseCode;
    Spinner spFaculty;
    Button btnSave;
    DBHelper dbHelper;
    ArrayList<Faculty> faculties;
    int preselectedFaculty = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        etCourseName = findViewById(R.id.etCourseName);
        etCourseCode = findViewById(R.id.etCourseCode);
        spFaculty = findViewById(R.id.spFaculty);
        btnSave = findViewById(R.id.btnSaveCourse);

        dbHelper = new DBHelper(this);
        preselectedFaculty = getIntent().getIntExtra("faculty_id", -1);

        loadFaculties();

        btnSave.setOnClickListener(v -> {
            String name = etCourseName.getText().toString().trim();
            String code = etCourseCode.getText().toString().trim();

            if (name.isEmpty() || code.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            int facultyId = faculties.get(spFaculty.getSelectedItemPosition()).getId();

            dbHelper.addCourse(name, code, facultyId);
            Toast.makeText(this, "Course added", Toast.LENGTH_SHORT).show();
            finish();
        });
        Button btnGoToList = findViewById(R.id.btnGoToList);
        btnGoToList.setOnClickListener(v ->
                startActivity(new Intent(CourseCreateActivity.this, CourseListActivity.class))
        );
    }

    private void loadFaculties() {
        Cursor c = dbHelper.getAllFaculties();
        faculties = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                Faculty f = new Faculty(c.getInt(0), c.getString(1), c.getString(2));
                faculties.add(f);
                names.add(f.getFacultyName());
            } while (c.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFaculty.setAdapter(adapter);

        if (preselectedFaculty != -1) {
            for (int i = 0; i < faculties.size(); i++) {
                if (faculties.get(i).getId() == preselectedFaculty) {
                    spFaculty.setSelection(i);
                    break;
                }
            }
        }
    }
}

