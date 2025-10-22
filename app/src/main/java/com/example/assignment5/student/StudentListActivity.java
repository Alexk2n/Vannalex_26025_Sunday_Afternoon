package com.example.assignment5.student;



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
import com.example.assignment5.model.Student;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayList<Student> studentList;
    ArrayAdapter<Student> adapter;
    ListView listView;
    Button btnAddStudent;
    int facultyId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listViewStudents);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        // if called from FacultyListActivity
        facultyId = getIntent().getIntExtra("faculty_id", -1);

        loadStudents();

        btnAddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentCreateActivity.class);
            if (facultyId != -1)
                intent.putExtra("faculty_id", facultyId);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Student s = studentList.get(position);
            Intent intent = new Intent(this, StudentDetailActivity.class);
            intent.putExtra("student_id", s.getId());
            startActivity(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Student selected = studentList.get(position);
            showOptionsDialog(selected);
            return true;
        });

        Button btnBackMain = findViewById(R.id.btnBackMain);
        btnBackMain.setOnClickListener(v ->
                startActivity(new Intent(StudentListActivity.this, MainActivity.class))
        );
    }

    private void loadStudents() {
        Cursor cursor;
        if (facultyId != -1)
            cursor = dbHelper.getStudentsByFaculty(facultyId);
        else
            cursor = dbHelper.getAllStudents();

        studentList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Student s = new Student(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                );
                studentList.add(s);
            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listView.setAdapter(adapter);
    }

    private void showOptionsDialog(Student student) {
        String[] options = {"Edit", "Delete", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(student.getLastName());
        builder.setTitle(student.getFirstName());
        builder.setItems(options, (DialogInterface dialog, int which) -> {
            switch (which) {
                case 0:
                    Intent i = new Intent(this, StudentEditActivity.class);
                    i.putExtra("student_id", student.getId());
                    startActivity(i);
                    break;
                case 1:
                    dbHelper.deleteStudent(student.getId());
                    Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show();
                    loadStudents();
                    break;
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }
}

