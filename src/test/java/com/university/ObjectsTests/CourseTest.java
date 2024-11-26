package com.university.ObjectsTests;

import com.university.Evaluations.Types.WRITTEN;
import org.junit.jupiter.api.Test;
import com.university.Objects.*;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    public void testCourseCreation() {
        COURSE course = new COURSE("Mathematics");
        assertEquals("Mathematics", course.getSubject());
    }

    @Test
    public void testAddEvaluation() {
        COURSE course = new COURSE("Mathematics");
        WRITTEN writtenExam = new WRITTEN("Jane Doe", "Mathematics", "Midterm", "Algebra", 8.0);
        course.getEvaluations().add(writtenExam);
        assertTrue(course.getEvaluations().contains(writtenExam));
    }

    @Test
    public void testEqualsAndHashCode() {
        COURSE course1 = new COURSE("Mathematics");
        COURSE course2 = new COURSE("Mathematics");
        assertEquals(course1, course2);
        assertEquals(course1.hashCode(), course2.hashCode());
    }
}
