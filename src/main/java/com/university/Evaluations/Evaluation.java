package com.university.Evaluations;

public abstract class Evaluation {

    private String student;
    private String subject;
    private String evaluationName;
    private String exerciseName;
    private double grade;

    public Evaluation(String student, String subject, String evaluationName, String exerciseName, double grade) {
        this.student = student;
        this.subject = subject;
        this.evaluationName = evaluationName;
        this.exerciseName = exerciseName;
        this.grade = grade;
    }

    public String getStudent() {
        return student;
    }

    public String getSubject() {
        return subject;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public double getGrade() {
        return grade;
    }

    public abstract String getType();

    public abstract String getEvaluationKind();
}
