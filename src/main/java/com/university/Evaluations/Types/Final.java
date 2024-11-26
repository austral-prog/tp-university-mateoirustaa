package com.university.Evaluations.Types;

import com.university.Objects.Evaluation;

public class Final extends Evaluation {

    public Final(String student, String subject, String evaluationName, String exerciseName, double grade) {
        super(student, subject, evaluationName, exerciseName, grade);
    }

    @Override
    public String getType() {
        return "FINAL_PRACTICAL_WORK";
    }

    @Override
    public String getEvaluationKind() {
        return "Final Practical";
    }
}
