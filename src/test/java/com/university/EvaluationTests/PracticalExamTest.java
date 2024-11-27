package com.university.EvaluationTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.university.Evaluations.Types.*;
public class PracticalExamTest {

    @Test
    public void testPracticalEvaluation() {
        Practical practicalWork = new Practical("John Doe", "Biology", "Practical Work", "Lab Experiment");
        assertEquals("PRACTICAL_WORK", practicalWork.getType());
        assertEquals("Practical Work", practicalWork.getEvaluationKind());
    }
}
