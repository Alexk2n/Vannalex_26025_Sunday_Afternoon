package com.example.assignment5.model;



public class Course {
    private int id;
    private String courseName;
    private String code;
    private int facultyId;

    public Course() {}

    public Course(int id, String courseName, String code, int facultyId) {
        this.id = id;
        this.courseName = courseName;
        this.code = code;
        this.facultyId = facultyId;
    }

    public Course(String courseName, String code, int facultyId) {
        this.courseName = courseName;
        this.code = code;
        this.facultyId = facultyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public String toString() {
        return courseName + " (" + code + ")";
    }
}
