package com.university.Evaluations.Types;

import com.university.Objects.Evaluation;

public class Written extends Evaluation {

    public Written(String student, String subject, String evaluationName, String exerciseName) {
        super(student, subject, evaluationName, exerciseName);
    }

    @Override
    public String getType() {
        return "WRITTEN_EXAM";
    }

    @Override
    public String getEvaluationKind() {
        return "Written Exam";
    }
}
