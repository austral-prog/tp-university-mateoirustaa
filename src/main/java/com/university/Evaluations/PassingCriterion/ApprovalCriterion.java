package com.university.Evaluations.PassingCriterion;

import java.util.List;

public abstract class ApprovalCriterion {

    public abstract List<String> getApprovalStatus();

    abstract void calculateApprovalStatus(List<Double> grades, Double criterionValue);
}
