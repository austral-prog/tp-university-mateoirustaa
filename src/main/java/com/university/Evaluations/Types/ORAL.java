package com.university.Evaluations.Types;

import com.university.Objects.Evaluation;

public class ORAL extends Evaluation {

    public ORAL(String student, String subject, String evaluationName, String exerciseName, double grade) {
        super(student, subject, evaluationName, exerciseName, grade);
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
