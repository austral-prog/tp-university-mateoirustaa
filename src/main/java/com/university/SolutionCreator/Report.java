package com.university.SolutionCreator;

import com.university.Objects.Student;
import com.university.Sorters.StudentSorter;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private static List<String[]> fileData = new ArrayList<>();

    public Report(List<Student> Students) {
        StudentSorter studentSorter = new StudentSorter(Students);
        List<Student> orderedStudents = studentSorter.getOrderedStudents();
        for (Student student : orderedStudents) {
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