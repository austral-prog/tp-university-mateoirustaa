package com.university.EvaluationTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.university.Evaluations.Types.*;

public class OralExamTest {

    @Test
    public void testOralEvaluation() {
        // Arrange
        Oral oralExam = new Oral("Jane Smith", "History", "Oral Exam", "Final Oral Exam");

        // Assert
        assertEquals("ORAL_EXAM", oralExam.getType());
        assertEquals("Oral", oralExam.getEvaluationKind());
    }
}
