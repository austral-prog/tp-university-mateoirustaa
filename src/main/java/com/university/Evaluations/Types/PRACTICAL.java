package com.university.Evaluations.Types;

import com.university.Evaluations.Evaluation;

public class PRACTICAL extends Evaluation {

    public PRACTICAL(String student, String subject, String evaluationName, String exerciseName, double grade) {
        super(student, subject, evaluationName, exerciseName, grade);
    }

    @Override
    public String getType() {
        return "PRACTICAL_WORK";
    }

    @Override
    public String getEvaluationKind() {
        return "Practical Work";
    }
}
