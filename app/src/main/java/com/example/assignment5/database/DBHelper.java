package com.example.assignment5.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student_manager.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_FACULTY = "faculty";
    public static final String TABLE_STUDENT = "student";
    public static final String TABLE_COURSE = "course";

    // Common columns
    public static final String COLUMN_ID = "id";

    // Faculty columns
    public static final String COLUMN_FACULTY_NAME = "faculty_name";
    public static final String COLUMN_DEAN_NAME = "dean_name";

    // Student columns
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FACULTY_ID = "faculty_id";

    // Course columns
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_CODE = "code";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        db.execSQL("CREATE TABLE " + TABLE_FACULTY + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FACULTY_NAME + " TEXT NOT NULL, "
                + COLUMN_DEAN_NAME + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_STUDENT + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FIRST_NAME + " TEXT NOT NULL, "
                + COLUMN_LAST_NAME + " TEXT NOT NULL, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_FACULTY_ID + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_FACULTY_ID + ") REFERENCES "
                + TABLE_FACULTY + "(" + COLUMN_ID + ") ON DELETE CASCADE)");

        db.execSQL("CREATE TABLE " + TABLE_COURSE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COURSE_NAME + " TEXT NOT NULL, "
                + COLUMN_CODE + " TEXT NOT NULL, "
                + COLUMN_FACULTY_ID + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_FACULTY_ID + ") REFERENCES "
                + TABLE_FACULTY + "(" + COLUMN_ID + ") ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACULTY);
        onCreate(db);
    }

    // ---------- Faculty CRUD ----------
    public long addFaculty(String name, String dean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FACULTY_NAME, name);
        cv.put(COLUMN_DEAN_NAME, dean);
        return db.insert(TABLE_FACULTY, null, cv);
    }

    public Cursor getAllFaculties() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_FACULTY, null);
    }

    public int updateFaculty(int id, String name, String dean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FACULTY_NAME, name);
        cv.put(COLUMN_DEAN_NAME, dean);
        return db.update(TABLE_FACULTY, cv, "id=?", new String[]{String.valueOf(id)});
    }

    public void deleteFaculty(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FACULTY, "id=?", new String[]{String.valueOf(id)});
    }

    // ---------- Student CRUD ----------
    public long addStudent(String fname, String lname, String email, int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, fname);
        cv.put(COLUMN_LAST_NAME, lname);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_FACULTY_ID, facultyId);
        return db.insert(TABLE_STUDENT, null, cv);
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT s.*, f." + COLUMN_FACULTY_NAME +
                " AS faculty_name FROM " + TABLE_STUDENT + " s " +
                "LEFT JOIN " + TABLE_FACULTY + " f ON s." + COLUMN_FACULTY_ID +
                " = f." + COLUMN_ID, null);
    }

    public Cursor getStudentsByFaculty(int facultyId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_STUDENT +
                " WHERE " + COLUMN_FACULTY_ID + "=?", new String[]{String.valueOf(facultyId)});
    }

    public int updateStudent(int id, String fname, String lname, String email, int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, fname);
        cv.put(COLUMN_LAST_NAME, lname);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_FACULTY_ID, facultyId);
        return db.update(TABLE_STUDENT, cv, "id=?", new String[]{String.valueOf(id)});
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, "id=?", new String[]{String.valueOf(id)});
    }

    // ---------- Course CRUD ----------
    public long addCourse(String name, String code, int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COURSE_NAME, name);
        cv.put(COLUMN_CODE, code);
        cv.put(COLUMN_FACULTY_ID, facultyId);
        return db.insert(TABLE_COURSE, null, cv);
    }

    public Cursor getCoursesByFaculty(int facultyId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_COURSE +
                " WHERE " + COLUMN_FACULTY_ID + "=?", new String[]{String.valueOf(facultyId)});
    }

    public int updateCourse(int id, String name, String code, int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COURSE_NAME, name);
        cv.put(COLUMN_CODE, code);
        cv.put(COLUMN_FACULTY_ID, facultyId);
        return db.update(TABLE_COURSE, cv, "id=?", new String[]{String.valueOf(id)});
    }

    public void deleteCourse(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE, "id=?", new String[]{String.valueOf(id)});
    }
    // In DBHelper.java
    public Cursor getAllCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT c.id, c.course_name, c.course_code, c.faculty_id, f.faculty_name " +
                        "FROM courses c LEFT JOIN faculties f ON c.faculty_id = f.id",
                null
        );
    }

}

