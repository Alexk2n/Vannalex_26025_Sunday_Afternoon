package com.example.assignment5.faculty;



import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.R;
import com.example.assignment5.database.DBHelper;

public class FacultyDetailActivity extends AppCompatActivity {

    TextView tvFacultyName, tvDean;
    DBHelper dbHelper;
    int facultyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_detail);

        tvFacultyName = findViewById(R.id.txtFacultyName);
        tvDean = findViewById(R.id.txtDeanName);
        dbHelper = new DBHelper(this);

        facultyId = getIntent().getIntExtra("faculty_id", -1);
        loadFaculty();
    }

    private void loadFaculty() {
        Cursor c = dbHelper.getAllFaculties();
        while (c.moveToNext()) {
            if (c.getInt(0) == facultyId) {
                tvFacultyName.setText("Faculty: " + c.getString(1));
                tvDean.setText("Dean: " + c.getString(2));
                break;
            }
        }
    }
}

