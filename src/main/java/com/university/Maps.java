package com.university;

import com.university.Objects.COURSE;
import com.university.Objects.STUDENT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps {

    public static Map<String, STUDENT> estudiantePorNombre = new HashMap<>();
    public static Map<STUDENT, List<COURSE>> materiaPorEstudiante = new HashMap<>();
}
