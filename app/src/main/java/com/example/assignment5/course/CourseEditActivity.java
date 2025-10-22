package com.example.assignment5.course;



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

public class CourseEditActivity extends AppCompatActivity {

    EditText etCourseName, etCourseCode;
    Spinner spFaculty;
    Button btnUpdate;
    DBHelper dbHelper;
    ArrayList<Faculty> faculties;
    int courseId, selectedFacultyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);

        etCourseName = findViewById(R.id.etCourseNameEdit);
        etCourseCode = findViewById(R.id.etCourseCodeEdit);
        spFaculty = findViewById(R.id.spFacultyEdit);
        btnUpdate = findViewById(R.id.btnUpdateCourse);

        dbHelper = new DBHelper(this);
        courseId = getIntent().getIntExtra("course_id", -1);

        loadFaculties();
        loadCourse();

        btnUpdate.setOnClickListener(v -> {
            selectedFacultyId = faculties.get(spFaculty.getSelectedItemPosition()).getId();
            dbHelper.updateCourse(courseId,
                    etCourseName.getText().toString(),
                    etCourseCode.getText().toString(),
                    selectedFacultyId);
            Toast.makeText(this, "Course updated", Toast.LENGTH_SHORT).show();
            finish();
        });
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
    }

    private void loadCourse() {
        Cursor c = dbHelper.getAllCourses();
        while (c.moveToNext()) {
            if (c.getInt(0) == courseId) {
                etCourseName.setText(c.getString(1));
                etCourseCode.setText(c.getString(2));
                int facultyId = c.getInt(3);

                for (int i = 0; i < faculties.size(); i++) {
                    if (faculties.get(i).getId() == facultyId) {
                        spFaculty.setSelection(i);
                        break;
                    }
                }
                break;
            }
        }
    }
}

