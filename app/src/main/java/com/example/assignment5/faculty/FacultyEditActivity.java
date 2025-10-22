package com.example.assignment5.faculty;



import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.R;
import com.example.assignment5.database.DBHelper;

public class FacultyEditActivity extends AppCompatActivity {

    EditText etName, etDean;
    Button btnUpdate;
    DBHelper dbHelper;
    int facultyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_edit);

        etName = findViewById(R.id.edtFacultyNameEdit);
        etDean = findViewById(R.id.edtDeanNameEdit);
        btnUpdate = findViewById(R.id.btnUpdateFaculty);
        dbHelper = new DBHelper(this);

        facultyId = getIntent().getIntExtra("faculty_id", -1);
        loadFacultyData();

        btnUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String dean = etDean.getText().toString().trim();

            dbHelper.updateFaculty(facultyId, name, dean);
            Toast.makeText(this, "Faculty updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void loadFacultyData() {
        Cursor c = dbHelper.getAllFaculties();
        while (c.moveToNext()) {
            if (c.getInt(0) == facultyId) {
                etName.setText(c.getString(1));
                etDean.setText(c.getString(2));
                break;
            }
        }
    }
}

