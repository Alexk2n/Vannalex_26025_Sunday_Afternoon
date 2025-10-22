package com.example.assignment5.faculty;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment5.MainActivity;
import com.example.assignment5.R;
import com.example.assignment5.database.DBHelper;
import com.example.assignment5.model.Faculty;
import com.example.assignment5.student.StudentListActivity;
import com.example.assignment5.course.CourseListActivity;

import java.util.ArrayList;

public class FacultyListActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayList<Faculty> facultyList;
    ArrayAdapter<Faculty> adapter;
    ListView listView;
    Button btnAddFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_list);

        dbHelper = new DBHelper(this);
        //listView = findViewById(R.id.listViewFaculties);
        listView = findViewById(R.id.listViewFaculty); // match XML ID

        btnAddFaculty = findViewById(R.id.btnAddFaculty);

        loadFaculties();

        btnAddFaculty.setOnClickListener(v -> {
            startActivity(new Intent(this, FacultyCreateActivity.class));
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Faculty selected = facultyList.get(position);
            Intent intent = new Intent(this, FacultyDetailActivity.class);
            intent.putExtra("faculty_id", selected.getId());
            startActivity(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Faculty selected = facultyList.get(position);
            showOptionsDialog(selected);
            return true;
        });
        Button btnBackMain = findViewById(R.id.btnBackMain);
        btnBackMain.setOnClickListener(v ->
                startActivity(new Intent(FacultyListActivity.this, MainActivity.class))
        );

    }

    private void loadFaculties() {
        Cursor cursor = dbHelper.getAllFaculties();
        facultyList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Faculty f = new Faculty(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                facultyList.add(f);
            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, facultyList);
        listView.setAdapter(adapter);
    }

    private void showOptionsDialog(Faculty faculty) {
        String[] options = {"View Students", "View Courses", "Edit", "Delete", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(faculty.getFacultyName());
        builder.setItems(options, (DialogInterface dialog, int which) -> {
            switch (which) {
                case 0:
                    Intent i1 = new Intent(this, StudentListActivity.class);
                    i1.putExtra("faculty_id", faculty.getId());
                    startActivity(i1);
                    break;
                case 1:
                    Intent i2 = new Intent(this, CourseListActivity.class);
                    i2.putExtra("faculty_id", faculty.getId());
                    startActivity(i2);
                    break;
                case 2:
                    Intent i3 = new Intent(this, FacultyEditActivity.class);
                    i3.putExtra("faculty_id", faculty.getId());
                    startActivity(i3);
                    break;
                case 3:
                    dbHelper.deleteFaculty(faculty.getId());
                    Toast.makeText(this, "Faculty deleted", Toast.LENGTH_SHORT).show();
                    loadFaculties();
                    break;
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFaculties();
    }
}

