package com.university.ObjectsTests;

import org.junit.jupiter.api.Test;
import com.university.Objects.*;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testStudentCreation() {
        STUDENT student = new STUDENT("John Doe", "johndoe@email.com");
        assertEquals("John Doe", student.getFullName());
    }

    @Test
    public void testAddSubject() {
        STUDENT student = new STUDENT("John Doe", "johndoe@email.com");
        student.addSubject("Mathematics");
        assertEquals(1, student.getSubjectsAmount());
    }

    @Test
    public void testEqualsAndHashCode() {
        STUDENT student1 = new STUDENT("John Doe", "johndoe@email.com");
        STUDENT student2 = new STUDENT("John Doe", "johndoe@email.com");
        assertEquals(student1, student2);
        assertEquals(student1.hashCode(), student2.hashCode());
    }
}
