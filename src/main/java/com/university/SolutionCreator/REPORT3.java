package com.university.SolutionCreator;

import java.util.ArrayList;
import java.util.List;

public class REPORT3 {

    private List<String[]> fileData = new ArrayList<>();
    private List<String[]> evaluationsData = new ArrayList<>();

    public REPORT3(List<String[]> gradedStudents, List<String[]> evaluations) {
        for (String[] studentData : gradedStudents) {
            if (studentData.length >= 3) {
                String studentName = studentData[0];
                String subjectName = studentData[1];
                String approvalStatus = studentData[2];
                fileData.add(new String[]{studentName, subjectName, approvalStatus});
            } else {
                break;
            }
        }

        for (String[] evaluationData : evaluations) {
            if (evaluationData.length >= 3) {
                String studentName = evaluationData[0];
                String subjectName = evaluationData[1];
                String approvalStatus = evaluationData[2];
                evaluationsData.add(new String[]{studentName, subjectName, approvalStatus});
            } else {
                break;
            }
        }
    }

    public List<String[]> getFileData() {
        return fileData;
    }

    public List<String[]> getEvaluationsData() {
        return evaluationsData;
    }
}
