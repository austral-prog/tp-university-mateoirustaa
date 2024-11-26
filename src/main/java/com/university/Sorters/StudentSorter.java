package com.university.Sorters;

import com.university.Objects.Student;

import java.util.Comparator;
import java.util.List;

public class StudentSorter {

    private List<Student> sortedStudents;

    public StudentSorter(List<Student> students) {
        this.sortedStudents = students.stream().sorted().toList();
        System.out.println(this.sortedStudents);
    }

    public List<Student> getOrderedStudents() {
        return sortedStudents;
    }
}
