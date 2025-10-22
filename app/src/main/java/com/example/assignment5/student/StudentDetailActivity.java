package com.example.assignment5.student;



import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.R;
import com.example.assignment5.database.DBHelper;

public class StudentDetailActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvPhone, tvFaculty;
    DBHelper dbHelper;
    int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        tvName = findViewById(R.id.txtStudentName);
        tvEmail = findViewById(R.id.tvStudentEmail);
        tvPhone = findViewById(R.id.tvStudentPhone);
        tvFaculty = findViewById(R.id.txtStudentFaculty);

        dbHelper = new DBHelper(this);
        studentId = getIntent().getIntExtra("student_id", -1);

        loadStudentDetails();
    }

    private void loadStudentDetails() {
        Cursor c = dbHelper.getStudentsByFaculty(studentId);

        while (c.moveToNext()) {
            if (c.getInt(0) == studentId) {
                tvName.setText("Name: " + c.getString(1));
                tvEmail.setText("Email: " + c.getString(2));
                tvPhone.setText("Phone: " + c.getString(3));
                tvFaculty.setText("Faculty: " + c.getString(5));
                break;
            }
        }
    }
}

