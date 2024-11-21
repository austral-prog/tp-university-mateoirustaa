package com.university.Objects;

import com.university.CLI.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class STUDENT implements Entity {
    private String fullName;
    private String student_Email;
    private List<String> subjects_enlisted;
    private int id;

    public STUDENT(String fullName, String studentEmail) {
        this.fullName = fullName;
        this.student_Email = studentEmail;
        this.id = id;
        this.subjects_enlisted = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public int getSubjectsAmount() {
        return subjects_enlisted.size();
    }

    public void addSubject(String subject) {
        if (!subjects_enlisted.contains(subject)) {
            subjects_enlisted.add(subject);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        STUDENT student = (STUDENT) obj;
        return Objects.equals(fullName, student.fullName);
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "STUDENT{" +
                "fullName='" + fullName + '\'' +
                ", student_Email='" + student_Email + '\'' +
                ", subjects_enlisted=" + subjects_enlisted +
                ", id=" + id +
                '}';
    }
}
