package com.university;

import com.university.Creators.*;
import com.university.Evaluations.Evaluation;
import com.university.SolutionCreator.REPORT;
import com.university.SolutionCreator.REPORT2;
import com.university.SolutionCreator.REPORT3;
import com.university.CSV.*;
import java.util.List;

public class App {

    public static void main(String[] args) {


        String[] studentHeaders = {"Student_Name", "Course_Count"};
        String inputStudentFile = "src/main/resources/input.csv";
        String outputStudentFile = "src/main/resources/solution.csv";


        CREATOR_1 studentCreator = new CREATOR_1(inputStudentFile);
        studentCreator.create();


        CSV_WRITER studentCsvWriter = new CSV_WRITER(studentHeaders, outputStudentFile,
                (new REPORT(studentCreator.getData())).getFileData());
        studentCsvWriter.write(",");

        String[] evaluationHeaders = {"Course_Name", "Evaluation_Name", "Student_Name", "Grade"};
        String inputEvaluationFile = "src/main/resources/input_2.csv";
        String outputEvaluationFile = "src/main/resources/solution_2.csv";


        CREATOR_2 evaluationCreator = new CREATOR_2(inputEvaluationFile);
        evaluationCreator.create();
        List<Evaluation> evaluationList = evaluationCreator.getData();


        REPORT2 evaluationReport = new REPORT2(evaluationList);
        CSV_WRITER evaluationCsvWriter = new CSV_WRITER(evaluationHeaders, outputEvaluationFile,
                evaluationReport.getFileData());
        evaluationCsvWriter.write(",");


        String[] approvalHeaders = {"Student_Name", "Course_Name", "Approval_Status"};
        String[] evaluationStatusHeaders = {"Student_Name", "Course_Name", "Evaluation_Status"};

        String inputApprovalFile = "src/main/resources/input_3.csv";
        String outputApprovalFile = "src/main/resources/solution_3.csv";


        CREATOR_3 approvalCreator = new CREATOR_3(inputApprovalFile);
        approvalCreator.create();
        List<String[]> evaluatedStudents = approvalCreator.getData();
        List<String[]> evaluationStatuses = approvalCreator.getEvaluations();


        REPORT3 approvalReport = new REPORT3(evaluatedStudents, evaluationStatuses);
        CSV_WRITER approvalCsvWriter = new CSV_WRITER(approvalHeaders, outputApprovalFile,
                approvalReport.getFileData());
        approvalCsvWriter.write(",");
    }
}
