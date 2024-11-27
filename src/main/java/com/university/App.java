package com.university;

import com.university.Creators.*;
import com.university.Objects.Evaluation;
import com.university.Objects.Student;
import com.university.Objects.Course;
import com.university.SolutionCreator.Report;
import com.university.SolutionCreator.Report2;
import com.university.SolutionCreator.Report3;
import com.university.CLI.UniversityCLI;
import com.university.CLI.Repositories.StudentRepo;
import com.university.CLI.Repositories.CourseRepo;
import com.university.CLI.Repositories.EvaluationRepo;
import com.university.CLI.CRUDRepository;
import com.university.CSV.*;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        try {
            Files.deleteIfExists(Paths.get("src/main/resources/solution.csv"));
            Files.deleteIfExists(Paths.get("src/main/resources/solution_2.csv"));
            Files.deleteIfExists(Paths.get("src/main/resources/solution_3.csv"));
        } catch (IOException e) {
            System.err.println("Error al limpiar archivos de solución: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            String inputStudentFile = "src/main/resources/input.csv";
            if (!Files.exists(Paths.get(inputStudentFile))) {
                throw new IOException("El archivo de entrada de estudiantes no existe: " + inputStudentFile);
            }
            String outputStudentFile = "src/main/resources/solution.csv";
            Creator1 studentCreator = new Creator1(inputStudentFile);
            studentCreator.create();
            List<String[]> studentData = new Report(studentCreator.getData()).getFileData();
            String[] studentHeaders = {"Student_Name", "Course_Count"};
            CsvWriter studentCsvWriter = new CsvWriter(studentHeaders, outputStudentFile, studentData);
            studentCsvWriter.write(",");
        } catch (IOException e) {
            System.err.println("Error al procesar estudiantes: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            String inputEvaluationFile = "src/main/resources/input_2.csv";
            if (!Files.exists(Paths.get(inputEvaluationFile))) {
                throw new IOException("El archivo de entrada de evaluaciones no existe: " + inputEvaluationFile);
            }
            String outputEvaluationFile = "src/main/resources/solution_2.csv";
            Creator2 evaluationCreator = new Creator2(inputEvaluationFile);
            evaluationCreator.create();
            List<Evaluation> evaluationList = evaluationCreator.getData();
            String[] evaluationHeaders = {"Subject_Name", "Criteria_Type", "Criteria_Value", "Evaluation_Name"};
            Report2 evaluationReport = new Report2(evaluationList);
            CsvWriter evaluationCsvWriter = new CsvWriter(evaluationHeaders, outputEvaluationFile, evaluationReport.getFileData());
            evaluationCsvWriter.write(",");
        } catch (IOException e) {
            System.err.println("Error al procesar evaluaciones: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            String inputApprovalFile = "src/main/resources/input_3.csv";
            if (!Files.exists(Paths.get(inputApprovalFile))) {
                throw new IOException("El archivo de entrada de aprobaciones no existe: " + inputApprovalFile);
            }
            String outputApprovalFile = "src/main/resources/solution_3.csv";
            Creator3 approvalCreator = new Creator3(inputApprovalFile);
            approvalCreator.create();
            List<String[]> evaluatedStudents = approvalCreator.getData();
            List<String[]> evaluationStatuses = approvalCreator.getEvaluations();
            String[] approvalHeaders = {"Student_Name", "Evaluation_Status"};
            Report3 approvalReport = new Report3(evaluatedStudents, evaluationStatuses);
            CsvWriter approvalCsvWriter = new CsvWriter(approvalHeaders, outputApprovalFile, approvalReport.getFileData());
            approvalCsvWriter.write(",");
        } catch (IOException e) {
            System.err.println("Error al procesar aprobaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
}