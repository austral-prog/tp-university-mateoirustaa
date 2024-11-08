package com.university.SolutionCreator;

import com.university.StudentFolder.STUDENT;
import com.university.StudentFolder.STUDENT_SORTER;

import java.util.ArrayList;
import java.util.List;

public class REPORT {

    private List<String[]> fileData = new ArrayList<>();

    public REPORT(List<STUDENT> STUDENTS) {
        STUDENT_SORTER studentSorter = new STUDENT_SORTER(STUDENTS);
        List<STUDENT> orderedStudents = studentSorter.getOrderedStudents();
        for (STUDENT student : orderedStudents) {
            String[] row = {student.getFullName(), String.valueOf(student.getSubjectsAmount())};
            fileData.add(row);
        }
    }

    public List<String[]> getFileData() {
        return fileData;
    }
}
