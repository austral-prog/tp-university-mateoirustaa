package com.university.Evaluations.Types;

import com.university.Objects.Evaluation;

public class Oral extends Evaluation {

    public Oral(String student, String subject, String evaluationName, String exerciseName) {
        super(student, subject, evaluationName, exerciseName);
    }

    @Override
    public String getType() {
        return "ORAL_EXAM";
    }

    @Override
    public String getEvaluationKind() {
        return "Oral";
    }
}
