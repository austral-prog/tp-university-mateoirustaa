package com.university.CLI.Repositories;

import com.university.CLI.CRUDRepository;
import com.university.Objects.Student;
import com.university.CLI.Exceptions.NullEntityException;

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
        return students.get(id);
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
