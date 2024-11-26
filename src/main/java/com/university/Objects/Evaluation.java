package com.university.Objects;

import com.university.CLI.Entity;

public abstract class Evaluation implements Entity {

    private int id;
    private String student;
    private String subject;
    private String evaluationName;
    private String exerciseName;
    public double grade;

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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Evaluation{id=" + id +
                ", student='" + student + '\'' +
                ", subject='" + subject + '\'' +
                ", evaluationName='" + evaluationName + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", grade=" + grade + '}';
    }
}
