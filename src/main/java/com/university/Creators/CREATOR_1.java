package com.university.Creators;

import com.university.CSV.CSV_READER;
import com.university.StudentFolder.STUDENT;
import com.university.Course.COURSE;

import java.util.*;

import static com.university.mapping.materiaPorEstudiante;
import static com.university.mapping.estudiantePorNombre;

public class CREATOR_1 implements CREATOR<STUDENT> {

    private String nombreArchivo;

    public CREATOR_1(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void create() {
        try {
            CSV_READER lectorCSV = new CSV_READER(nombreArchivo);
            lectorCSV.read(",");
            List<String[]> todosLosDatos = lectorCSV.getData();

            for (String[] datos : todosLosDatos) {
                if (datos.length != 0) {

                    String materia = datos[1];
                    String nombreEstudiante = datos[2];
                    String emailEstudiante = datos[3];

                    STUDENT estudiante = estudiantePorNombre.get(nombreEstudiante);

                    if (estudiante != null) {

                        STUDENT nuevoEstudiante = new STUDENT(nombreEstudiante, emailEstudiante);
                        COURSE nuevaMateria = new COURSE(materia);
                        estudiante.addSubject(materia);
                        materiaPorEstudiante.get(nuevoEstudiante).add(nuevaMateria);
                    }
                    else {

                        STUDENT nuevoEstudiante = new STUDENT(nombreEstudiante, emailEstudiante);
                        COURSE nuevaMateria = new COURSE(materia);

                        nuevoEstudiante.addSubject(materia);
                        estudiantePorNombre.put(nombreEstudiante, nuevoEstudiante);
                        materiaPorEstudiante.put(nuevoEstudiante, new ArrayList<>());
                        materiaPorEstudiante.get(nuevoEstudiante).add(nuevaMateria);
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<STUDENT> getData() {
        return new ArrayList<>(estudiantePorNombre.values());
    }
}
