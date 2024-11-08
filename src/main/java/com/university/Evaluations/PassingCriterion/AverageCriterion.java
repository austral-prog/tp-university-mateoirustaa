package com.university.Evaluations.PassingCriterion;

import java.util.ArrayList;
import java.util.List;

public class AverageCriterion extends ApprovalCriterion {
    private List<String> approvalStatus;

    public AverageCriterion(List<Double> grades, Double criterionValue) {
        this.approvalStatus = new ArrayList<>();
        calculateApprovalStatus(grades, criterionValue);
    }

    @Override
    void calculateApprovalStatus(List<Double> grades, Double criterionValue) {
        int total = 0;
        for (Double grade : grades) {
            total += grade;
        }
        double average = (double) total / grades.size();
        if (average >= criterionValue) {
            approvalStatus.add("Aprobado");
        } else {
            approvalStatus.add("Desaprobado");
        }
    }

    @Override
    public List<String> getApprovalStatus() {
        return approvalStatus;
    }
}
