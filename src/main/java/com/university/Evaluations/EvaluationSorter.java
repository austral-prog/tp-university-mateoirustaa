package com.university.Evaluations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvaluationSorter {

    private List<Evaluation> sortedEvaluations;

    public EvaluationSorter(List<Evaluation> evaluations) {

        sortedEvaluations = new ArrayList<>();


        Comparator<Evaluation> evaluationComparator = Comparator
                .comparing(Evaluation::getSubject)
                .thenComparing(Evaluation::getEvaluationName)
                .thenComparing(Evaluation::getStudent);


        evaluations.sort(evaluationComparator);


        sortedEvaluations.addAll(evaluations);

    }


    public List<Evaluation> getOrderedEvaluations() {
        return sortedEvaluations;
    }
}
