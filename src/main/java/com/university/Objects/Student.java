package com.university.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements Entity, Comparable<Student> {
    private String fullName;
    private String studentEmail;
    private List<String> subjectsEnlisted;
    private int id;

    public Student(String fullName, String studentEmail) {
        this.fullName = fullName;
        this.studentEmail = studentEmail;
        this.id = id;
        this.subjectsEnlisted = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectsAmount() {
        return subjectsEnlisted.size();
    }

    public void addSubject(String subject) {
        if (!subjectsEnlisted.contains(subject)) {
            subjectsEnlisted.add(subject);
        }
    }

    @Override
    public String toString() {
        return "\nStudent Name: " + fullName + "\nStudent Email: " + studentEmail;
    }

    @Override
    public int compareTo(Student o) {
        if (fullName == null) return -1;
        return this.fullName.compareTo(o.fullName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(fullName, student.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}
