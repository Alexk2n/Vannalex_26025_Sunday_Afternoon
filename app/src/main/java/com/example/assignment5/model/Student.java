package com.example.assignment5.model;



public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int facultyId;
    private String facultyName; // used in JOIN results

    public Student() {}

    public Student(int id, String firstName, String lastName, String email, int facultyId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.facultyId = facultyId;
    }

    public Student(String firstName, String lastName, String email, int facultyId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.facultyId = facultyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + (facultyName != null ? facultyName : "No Faculty") + ")";
    }
}

