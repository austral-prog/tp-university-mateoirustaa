package com.university.EvaluationTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.university.Evaluations.Types.*;

public class OralExamTest {

    @Test
    public void testOralEvaluation() {
        // Arrange
        ORAL oralExam = new ORAL("Jane Smith", "History", "Oral Exam", "Final Oral Exam", 8.0);

        // Assert
        assertEquals("ORAL_EXAM", oralExam.getType());
        assertEquals("Oral", oralExam.getEvaluationKind());
        assertEquals(8.0, oralExam.getGrade());
    }
}
