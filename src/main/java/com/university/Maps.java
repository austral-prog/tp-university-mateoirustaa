package com.university;

import com.university.Objects.Course;
import com.university.Objects.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps {

    public static Map<String, Student> estudiantePorNombre = new HashMap<>();
    public static Map<Student, List<Course>> materiaPorEstudiante = new HashMap<>();
}
