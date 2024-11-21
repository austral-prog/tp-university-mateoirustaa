package com.university.Sorters;

import com.university.Objects.STUDENT;

import java.util.Comparator;
import java.util.List;

public class STUDENT_SORTER {

    private List<STUDENT> sortedStudents;

    public STUDENT_SORTER(List<STUDENT> students) {
        students.sort(Comparator.comparing(STUDENT::getFullName));
        this.sortedStudents = students;
    }

    public List<STUDENT> getOrderedStudents() {
        return sortedStudents;
    }
}
