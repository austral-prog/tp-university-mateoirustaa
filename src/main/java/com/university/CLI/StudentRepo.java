package com.university.CLI;

import com.university.Objects.STUDENT;
import java.util.HashMap;
import java.util.Map;

public class StudentRepo implements CRUDRepository<STUDENT> {
    private final Map<Integer, STUDENT> students = new HashMap<>();

    @Override
    public void create(STUDENT student) {
        if (student != null) {
            students.put(student.getId(), student);
        }
    }

    @Override
    public STUDENT read(int id) {

        return students.get(id);
    }

    @Override
    public void update(int id, STUDENT student) {
        if (students.containsKey(id) && student != null) {
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
    public Class<STUDENT> getEntityClass() {

        return STUDENT.class;
    }
}
