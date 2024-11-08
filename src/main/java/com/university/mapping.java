package com.university;

import com.university.Course.COURSE;
import com.university.StudentFolder.STUDENT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mapping {

    public static Map<String, STUDENT> estudiantePorNombre = new HashMap<>();
    public static Map<STUDENT, List<COURSE>> materiaPorEstudiante = new HashMap<>();

}
