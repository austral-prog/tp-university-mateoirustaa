package com.university.Creators;

import com.university.CSV.CSV_READER;
import com.university.Objects.STUDENT;
import com.university.Objects.COURSE;

import java.util.*;

import static com.university.Maps.materiaPorEstudiante;
import static com.university.Maps.estudiantePorNombre;

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
                if (datos.length != 0 && !datos[0].equals("Student_Name")) {
                    String materia = datos[1];
                    String nombreEstudiante = datos[2];
                    String emailEstudiante = datos[3];

                    if (nombreEstudiante.equals("Student_Name")) {
                        continue;
                    }

                    STUDENT estudiante = estudiantePorNombre.get(nombreEstudiante);

                    if (estudiante != null) {
                        estudiante.addSubject(materia);
                        materiaPorEstudiante.get(estudiante).add(new COURSE(materia));
                    } else {
                        STUDENT nuevoEstudiante = new STUDENT(nombreEstudiante, emailEstudiante);
                        nuevoEstudiante.addSubject(materia);
                        estudiantePorNombre.put(nombreEstudiante, nuevoEstudiante);
                        materiaPorEstudiante.put(nuevoEstudiante, new ArrayList<>());
                        materiaPorEstudiante.get(nuevoEstudiante).add(new COURSE(materia));
                    }
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

    public CSV_READER getReader() {
        CSV_READER reader = new CSV_READER("");
        return reader;
    }
}
