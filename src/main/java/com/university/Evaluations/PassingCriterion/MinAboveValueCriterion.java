package com.university.Evaluations.PassingCriterion;

import java.util.ArrayList;
import java.util.List;

public class MinAboveValueCriterion extends ApprovalCriterion {
    private List<String> approvalStatus;

    public MinAboveValueCriterion(List<Double> grades, Double criterionValue) {
        this.approvalStatus = new ArrayList<>();
        calculateApprovalStatus(grades, criterionValue);
    }

    @Override
    void calculateApprovalStatus(List<Double> grades, Double criterionValue) {
        for (Double grade : grades) {
            if (grade >= criterionValue) {
                approvalStatus.add("Aprobado");
            } else {
                approvalStatus.add("Desaprobado");
            }
        }
    }

    @Override
    public List<String> getApprovalStatus() {
        return approvalStatus;
    }
}
