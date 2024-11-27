package com.university.ObjectsTests;

import org.junit.jupiter.api.Test;
import com.university.Objects.*;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testStudentCreation() {
        Student student = new Student("John Doe", "johndoe@email.com");
        assertEquals("John Doe", student.getFullName());
    }

    @Test
    public void testAddSubject() {
        Student student = new Student("John Doe", "johndoe@email.com");
        student.addSubject("Mathematics");
        assertEquals(1, student.getSubjectsAmount());
    }

    @Test
    public void testEqualsAndHashCode() {
        Student student1 = new Student("John Doe", "johndoe@email.com");
        Student student2 = new Student("John Doe", "johndoe@email.com");
        assertEquals(student1, student2);
        assertEquals(student1.hashCode(), student2.hashCode());
    }
}
