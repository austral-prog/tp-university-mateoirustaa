package com.university.SolutionCreator;

import com.university.Objects.Evaluation;
import com.university.Sorters.EvaluationSorter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Report2 {

    private List<String[]> fileData = new ArrayList<>();

    public Report2(List<Evaluation> evaluations) {
        EvaluationSorter evaluationSorter = new EvaluationSorter(evaluations);
        List<Evaluation> orderedEvaluations = evaluationSorter.getOrderedEvaluations();

        Set<String> uniqueRows = new HashSet<>();
        for (Evaluation evaluation : orderedEvaluations) {
            String row = evaluation.getSubject() + "," + evaluation.getEvaluationName() + "," + evaluation.getStudent() + "," + String.valueOf(evaluation.getGrade());
            if (!uniqueRows.contains(row)) {
                uniqueRows.add(row);
                fileData.add(new String[]{evaluation.getSubject(), evaluation.getEvaluationName(), evaluation.getStudent(), String.valueOf(evaluation.getGrade())});
            }
        }
    }

    public List<String[]> getFileData() {
        return fileData;
    }

    public void solution() {
    }
}
