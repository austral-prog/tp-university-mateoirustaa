package com.university.CLI.Repositories;

import com.university.Objects.Course;
import com.university.CLI.CRUDRepository;
import com.university.CLI.Exceptions.NullEntityException;
import java.util.HashMap;
import java.util.Map;

public class CourseRepo implements CRUDRepository<Course> {
    private final Map<Integer, Course> courses = new HashMap<>();

    @Override
    public void create(Course course) {
        if (course == null) {
            throw new NullEntityException("The course cannot be null.");
        }
        int id = courses.size() + 1;
        course.setId(id);
        courses.put(id, course);
    }

    @Override
    public Course read(int id) {
        return courses.get(id);
    }

    @Override
    public void update(int id, Course course) {
        if (course == null) {
            throw new NullEntityException("The course cannot be null.");
        }
        if (courses.containsKey(id)) {
            courses.put(id, course);
        } else {
            throw new IllegalArgumentException("Course with the specified ID does not exist.");
        }
    }

    @Override
    public void delete(int id) {
        courses.remove(id);
    }

    @Override
    public String getIdentifier() {
        return "Course";
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }
}
