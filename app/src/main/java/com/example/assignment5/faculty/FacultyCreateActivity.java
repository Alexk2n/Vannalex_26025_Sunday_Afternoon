package com.example.assignment5.faculty;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.R;
import com.example.assignment5.database.DBHelper;

public class FacultyCreateActivity extends AppCompatActivity {

    EditText etName, etDean;
    Button btnSave;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_create);

        etName = findViewById(R.id.edtFacultyName);
        etDean = findViewById(R.id.edtDeanName);
        btnSave = findViewById(R.id.btnSaveFaculty);
        dbHelper = new DBHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String dean = etDean.getText().toString().trim();

            if (name.isEmpty() || dean.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.addFaculty(name, dean);
            Toast.makeText(this, "Faculty added", Toast.LENGTH_SHORT).show();
            finish();
        });
        Button btnGoToList = findViewById(R.id.btnGoToList);
        btnGoToList.setOnClickListener(v ->
                startActivity(new Intent(FacultyCreateActivity.this, FacultyListActivity.class))
        );

    }
}

