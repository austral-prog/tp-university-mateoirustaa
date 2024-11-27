package com.university.Evaluations.Types;

import com.university.Objects.Evaluation;

public class Practical extends Evaluation {

    public Practical(String student, String subject, String evaluationName, String exerciseName) {
        super(student, subject, evaluationName, exerciseName);
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
