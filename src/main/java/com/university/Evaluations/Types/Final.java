package com.university.Evaluations.Types;

import com.university.Objects.Evaluation;

public class Final extends Evaluation {

    public Final(String student, String subject, String evaluationName, String exerciseName) {
        super(student, subject, evaluationName, exerciseName);
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
