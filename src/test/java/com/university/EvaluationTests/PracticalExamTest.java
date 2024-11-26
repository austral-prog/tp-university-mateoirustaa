package com.university.EvaluationTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.university.Evaluations.Types.*;
public class PracticalExamTest {

    @Test
    public void testPracticalEvaluation() {
        PRACTICAL practicalWork = new PRACTICAL("John Doe", "Biology", "Practical Work", "Lab Experiment", 7.5);
        assertEquals("PRACTICAL_WORK", practicalWork.getType());
        assertEquals("Practical Work", practicalWork.getEvaluationKind());
        assertEquals(7.5, practicalWork.getGrade());
    }
}
