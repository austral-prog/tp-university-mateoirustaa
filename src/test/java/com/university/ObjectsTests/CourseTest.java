package com.university.ObjectsTests;

import com.university.Evaluations.Types.Written;
import org.junit.jupiter.api.Test;
import com.university.Objects.*;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    public void testCourseCreation() {
        Course course = new Course("Mathematics");
        assertEquals("Mathematics", course.getSubject());
    }

    @Test
    public void testAddEvaluation() {
        Course course = new Course("Mathematics");
        Written writtenExam = new Written("Jane Doe", "Mathematics", "Midterm", "Algebra", 8.0);
        course.getEvaluations().add(writtenExam);
        assertTrue(course.getEvaluations().contains(writtenExam));
    }

    @Test
    public void testEqualsAndHashCode() {
        Course course1 = new Course("Mathematics");
        Course course2 = new Course("Mathematics");
        assertEquals(course1, course2);
        assertEquals(course1.hashCode(), course2.hashCode());
    }
}
