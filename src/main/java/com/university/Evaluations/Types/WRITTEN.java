package com.university.Evaluations.Types;

import com.university.Objects.Evaluation;

public class WRITTEN extends Evaluation {

    public WRITTEN(String student, String subject, String evaluationName, String exerciseName, double grade) {
        super(student, subject, evaluationName, exerciseName, grade);
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
