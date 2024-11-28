package com.university.CLI.Repositories;

import com.university.CLI.CRUDRepository;
import com.university.Objects.Student;
import com.university.CLI.Exceptions.NullEntityException;
import com.university.CLI.Exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class StudentRepo implements CRUDRepository<Student> {
    private final Map<Integer, Student> students = new HashMap<>();

    @Override
    public void create(Student student) {
        if (student == null) {
            throw new NullEntityException("The student cannot be null.");
        }
        students.put(student.getId(), student);
    }

    @Override
    public Student read(int id) {
        Student student = students.get(id);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + id + " not found.");
        }
        return student;
    }

    @Override
    public void update(int id, Student student) {
        if (student == null) {
            throw new NullEntityException("The student cannot be null.");
        }
        if (students.containsKey(id)) {
            students.put(id, student);
        }
    }

    @Override
    public void delete(int id) {
        students.remove(id);
    }

    @Override
    public String getIdentifier() {
        return "Student";
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
