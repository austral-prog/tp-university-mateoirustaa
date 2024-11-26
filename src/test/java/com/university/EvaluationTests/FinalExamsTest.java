package com.university.EvaluationTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.university.Evaluations.Types.*;

public class FinalExamsTest {

    @Test
    public void testConstructorAndGetMethods() {
        // Arrange
        String student = "John Doe";
        String subject = "Mathematics";
        String evaluationName = "Final Exam";
        String exerciseName = "Final Practical Work";
        double grade = 7.5;

        // Act
        Final finalPractical = new Final(student, subject, evaluationName, exerciseName, grade);

        // Assert
        assertNotNull(finalPractical, "The FINAL_PRACTICAL object should not be null.");
        assertEquals("FINAL_PRACTICAL_WORK", finalPractical.getType(), "The getType() method should return 'FINAL_PRACTICAL_WORK'.");
        assertEquals("Final Practical", finalPractical.getEvaluationKind(), "The getEvaluationKind() method should return 'Final Practical'.");
    }
}
