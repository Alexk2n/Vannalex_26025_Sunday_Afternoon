package com.example.assignment5.model;


public class Faculty {
    private int id;
    private String facultyName;
    private String deanName;

    public Faculty() {}

    public Faculty(int id, String facultyName, String deanName) {
        this.id = id;
        this.facultyName = facultyName;
        this.deanName = deanName;
    }

    public Faculty(String facultyName, String deanName) {
        this.facultyName = facultyName;
        this.deanName = deanName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }

    @Override
    public String toString() {
        return facultyName + " (Dean: " + deanName + ")";
    }
}

