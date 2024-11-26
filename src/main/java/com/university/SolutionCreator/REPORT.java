package com.university.SolutionCreator;

import com.university.Objects.STUDENT;
import com.university.Sorters.STUDENT_SORTER;

import java.util.ArrayList;
import java.util.List;

public class REPORT {

    private static List<String[]> fileData = new ArrayList<>();

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

    public static List<String> getStudent() {
        List<String> students = new ArrayList<>();
        for (String[] row : fileData) {
            students.add(row[0]);
        }
        return students;
    }

    public static List<Integer> getCourseCountOfStudents() {
        List<Integer> courseCountOfStudent = new ArrayList<>();
        for (String[] row : fileData) {
            courseCountOfStudent.add(Integer.parseInt(row[1]));
        }
        return courseCountOfStudent;
    }
}