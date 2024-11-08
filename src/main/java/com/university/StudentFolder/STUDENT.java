package com.university.StudentFolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class STUDENT {

    private String fullName;
    private String student_Email;
    private List<String> subjects_enlisted;

    public STUDENT(String fullName, String studentEmail) {
        this.subjects_enlisted = new ArrayList<>();
        this.fullName = fullName;
        this.student_Email = studentEmail;
    }

    public String getFullName() {
        return fullName;
    }

    public int getSubjectsAmount() {
        return subjects_enlisted.size();
    }

    public List<String> getSubjects() {
        return subjects_enlisted;
    }

    public void addSubject(String subject) {
        if (!subjects_enlisted.contains(subject)) {
            subjects_enlisted.add(subject);
        }
    }

    // Identity
    public int hashCode() {
        return Objects.hash(fullName);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        STUDENT student = (STUDENT) obj;
        return Objects.equals(fullName, student.fullName);
    }
}
