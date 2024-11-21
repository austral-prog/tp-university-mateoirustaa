package com.university;

import com.university.CLI.CLI;
import com.university.Creators.*;
import com.university.Objects.Evaluation;
import com.university.Objects.STUDENT;
import com.university.SolutionCreator.REPORT;
import com.university.SolutionCreator.REPORT2;
import com.university.SolutionCreator.REPORT3;
import com.university.CSV.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import com.university.CLI.UniversityCLI;
import com.university.CLI.StudentRepo;
import com.university.CLI.CRUDRepository;

public class App {

    public static void main(String[] args) {

        try {
            Files.deleteIfExists(Paths.get("src/main/resources/solution.csv"));
            Files.deleteIfExists(Paths.get("src/main/resources/solution_2.csv"));
            Files.deleteIfExists(Paths.get("src/main/resources/solution_3.csv"));
        } catch (IOException e) {
            System.err.println("Error al limpiar archivos de solución: " + e.getMessage());
        }
        String inputStudentFile = "src/main/resources/input.csv";
        String outputStudentFile = "src/main/resources/solution.csv";

        CREATOR_1 studentCreator = new CREATOR_1(inputStudentFile);
        studentCreator.create();

        List<String[]> studentData = new REPORT(studentCreator.getData()).getFileData();

        String[] studentHeaders = {"Student_Name", "Course_Count"};

        CSV_WRITER studentCsvWriter = new CSV_WRITER(studentHeaders, outputStudentFile, studentData);
        studentCsvWriter.write(",");

        String inputEvaluationFile = "src/main/resources/input_2.csv";
        String outputEvaluationFile = "src/main/resources/solution_2.csv";

        CREATOR_2 evaluationCreator = new CREATOR_2(inputEvaluationFile);
        evaluationCreator.create();
        List<Evaluation> evaluationList = evaluationCreator.getData();

        String[] evaluationHeaders = {"Subject_Name", "Criteria_Type", "Criteria_Value", "Evaluation_Name"};
        REPORT2 evaluationReport = new REPORT2(evaluationList);
        CSV_WRITER evaluationCsvWriter = new CSV_WRITER(evaluationHeaders, outputEvaluationFile, evaluationReport.getFileData());
        evaluationCsvWriter.write(",");

        String inputApprovalFile = "src/main/resources/input_3.csv";
        String outputApprovalFile = "src/main/resources/solution_3.csv";

        CREATOR_3 approvalCreator = new CREATOR_3(inputApprovalFile);
        approvalCreator.create();
        List<String[]> evaluatedStudents = approvalCreator.getData();
        List<String[]> evaluationStatuses = approvalCreator.getEvaluations();

        String[] approvalHeaders = {"Student_Name", "Evaluation_Status"};
        REPORT3 approvalReport = new REPORT3(evaluatedStudents, evaluationStatuses);
        CSV_WRITER approvalCsvWriter = new CSV_WRITER(approvalHeaders, outputApprovalFile, approvalReport.getFileData());
        approvalCsvWriter.write(",");

        CRUDRepository<STUDENT, Integer> studentRepo = new StudentRepo();
        CLI universityCLI = new UniversityCLI(REPORT.getStudent(), REPORT.getCourseCountOfStudents());
        CRUDRepository[] crudInterfaces = new CRUDRepository<?,?>[] { studentRepo };
        universityCLI.runCLI(crudInterfaces);

    }
}
