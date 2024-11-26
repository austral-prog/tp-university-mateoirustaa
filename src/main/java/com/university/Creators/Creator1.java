package com.university.Creators;

import com.university.CSV.CsvReader;
import com.university.Objects.Student;
import com.university.Objects.Course;

import java.util.*;
import java.util.UUID;

import static com.university.Maps.materiaPorEstudiante;
import static com.university.Maps.estudiantePorNombre;

public class Creator1 implements Creator<Student> {

    private String nombreArchivo;

    public Creator1(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void create() {
        try {
            CsvReader lectorCSV = new CsvReader(nombreArchivo);
            lectorCSV.read(",");
            List<String[]> todosLosDatos = lectorCSV.getData();

            for (String[] datos : todosLosDatos) {
                if (datos.length != 0 && !datos[0].equals("Student_Name")) {
                    String materia = datos[1];
                    String nombreEstudiante = datos[2];
                    String emailEstudiante = datos[3];

                    if (nombreEstudiante.equals("Student_Name")) {
                        continue;
                    }

                    Student estudiante = estudiantePorNombre.get(nombreEstudiante);

                    if (estudiante != null) {
                        estudiante.addSubject(materia);
                        materiaPorEstudiante.get(estudiante).add(new Course(materia));
                    } else {
                        String estudianteId = UUID.randomUUID().toString();
                        Student nuevoEstudiante = new Student(nombreEstudiante, emailEstudiante, estudianteId);
                        nuevoEstudiante.addSubject(materia);
                        estudiantePorNombre.put(nombreEstudiante, nuevoEstudiante);
                        materiaPorEstudiante.put(nuevoEstudiante, new ArrayList<>());
                        materiaPorEstudiante.get(nuevoEstudiante).add(new Course(materia));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getData() {
        return new ArrayList<>(estudiantePorNombre.values());
    }

    public CsvReader getReader() {
        return new CsvReader("");
    }
}
