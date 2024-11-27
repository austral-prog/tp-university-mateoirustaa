package com.university.EvaluationTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.university.Evaluations.Types.*;

public class WrittenExamTest {

    @Test
    public void testWrittenEvaluation() {
        // Arrange
        Written writtenExam = new Written("Jane Doe", "Mathematics", "Midterm Exam", "Algebra Test");

        // Assert
        assertEquals("WRITTEN_EXAM", writtenExam.getType());
        assertEquals("Written Exam", writtenExam.getEvaluationKind());
        assertEquals(8.0, writtenExam.getGrade());
    }
}
