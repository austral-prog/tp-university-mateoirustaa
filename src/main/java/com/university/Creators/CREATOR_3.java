package com.university.Creators;
import com.university.CSV.CSV_READER;
import com.university.StudentFolder.STUDENT;
import com.university.Course.COURSE;

import static com.university.mapping.materiaPorEstudiante;

import java.util.*;

public class CREATOR_3 implements CREATOR {

    private String filename;
    private List<String[]> data = new ArrayList<>();
    private Set<String[]> results = new HashSet<>();
    private List<String[]> evaluations = new ArrayList<>();

    public CREATOR_3(String filename) {
        this.filename = filename;
    }

    @Override
    public void create() {
        CSV_READER reader = new CSV_READER(filename);
        reader.read(",");
        data = reader.getData();

        for (String[] row : data) {
            if (row.length >= 3) {
                String subjectName = row[0];
                String criteriaType = row[1];
                double criteriaValue;

                try {
                    criteriaValue = Double.parseDouble(row[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Formato inválido en el valor del criterio: " + Arrays.toString(row));
                    continue;
                }

                for (STUDENT student : materiaPorEstudiante.keySet()) {
                    for (COURSE course : materiaPorEstudiante.get(student)) {
                        if (subjectName.equals(course.getSubject())) {
                            double finalGrade = course.getFinalGrade();
                            String approvalStatus = getApprovalStatus(finalGrade, criteriaType, criteriaValue);

                            if (approvalStatus != null) {
                                results.add(new String[]{student.getFullName(), subjectName, approvalStatus});
                                evaluations.add(new String[]{student.getFullName(), subjectName, approvalStatus});
                            }
                        }
                    }
                }
            }
        }
    }

    private String getApprovalStatus(double grade, String criteriaType, double criteriaValue) {
        switch (criteriaType) {
            case "AVERAGE_ABOVE_VALUE":
            case "MAX_ABOVE_VALUE":
            case "MIN_ABOVE_VALUE":
                return grade >= criteriaValue ? "Aprobado" : "Desaprobado";
            default:
                return null;
        }
    }

    @Override
    public List<String[]> getData() {
        return new ArrayList<>(results);
    }

    public List<String[]> getEvaluations() {
        return evaluations;
    }
}
