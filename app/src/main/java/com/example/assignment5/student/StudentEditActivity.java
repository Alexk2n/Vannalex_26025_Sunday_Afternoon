package com.example.assignment5.student;



import android.annotation.SuppressLint;
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

public class StudentEditActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone;
    Spinner spFaculty;
    Button btnUpdate;
    DBHelper dbHelper;
    ArrayList<Faculty> faculties;
    int studentId, selectedFacultyId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        etName = findViewById(R.id.edtStudentNameEdit);
        etEmail = findViewById(R.id.etStudentEmailEdit);
        etPhone = findViewById(R.id.etStudentPhoneEdit);
        spFaculty = findViewById(R.id.spinnerFacultyEdit);
        btnUpdate = findViewById(R.id.btnUpdateStudent);

        dbHelper = new DBHelper(this);
        studentId = getIntent().getIntExtra("student_id", -1);

        loadFaculties();
        loadStudent();

        btnUpdate.setOnClickListener(v -> {
            selectedFacultyId = faculties.get(spFaculty.getSelectedItemPosition()).getId();
            dbHelper.updateStudent(studentId,
                    etName.getText().toString(),
                    etEmail.getText().toString(),
                    etPhone.getText().toString(),
                    selectedFacultyId);
            Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show();
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

    private void loadStudent() {
        Cursor c = dbHelper.getAllStudents();
        while (c.moveToNext()) {
            if (c.getInt(0) == studentId) {
                etName.setText(c.getString(1));
                etEmail.setText(c.getString(2));
                etPhone.setText(c.getString(3));
                int facultyId = c.getInt(4);

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

