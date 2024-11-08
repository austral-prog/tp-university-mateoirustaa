package com.university.Creators;

import com.university.CSV.CSV_READER;
import com.university.Course.COURSE;
import com.university.Evaluations.*;
import com.university.Evaluations.Types.FINAL_PRACTICAL;
import com.university.Evaluations.Types.ORAL;
import com.university.Evaluations.Types.PRACTICAL;
import com.university.Evaluations.Types.WRITTEN;
import com.university.StudentFolder.STUDENT;

import java.util.ArrayList;
import java.util.List;

import static com.university.mapping.materiaPorEstudiante;
import static com.university.mapping.estudiantePorNombre;

public class CREATOR_2 implements CREATOR<Evaluation> {

    private String archivo;

    public CREATOR_2(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void create() {
        CSV_READER lectorCsv = new CSV_READER(archivo);
        lectorCsv.read(",");
        List<String[]> todosLosDatos = lectorCsv.getData();

        for (String[] dato : todosLosDatos) {
            if (dato.length != 0) {
                String nombreEstudiante = dato[0];
                String nombreMateria = dato[1];
                String tipoEvaluacion = dato[2];
                String nombreEvaluacion = dato[3];
                String nombreEjercicio = dato[4];
                double calificacion = 0;

                try {
                    calificacion = Double.parseDouble(dato[5]);
                } catch (NumberFormatException e) {
                    System.out.println("Formato de calificación inválido: " + dato[5]);
                    continue;
                }

                STUDENT estudiante = estudiantePorNombre.get(nombreEstudiante);
                if (estudiante == null) {
                    estudiante = new STUDENT(nombreEstudiante, "email");
                    estudiantePorNombre.put(nombreEstudiante, estudiante);
                }

                List<COURSE> materias = materiaPorEstudiante.computeIfAbsent(estudiante, k -> new ArrayList<>());

                COURSE materiaExistente = null;
                for (COURSE c : materias) {
                    if (c.getSubject().equals(nombreMateria)) {
                        materiaExistente = c;
                        break;
                    }
                }

                if (materiaExistente == null) {
                    materiaExistente = new COURSE(nombreMateria);
                    materias.add(materiaExistente);
                }

                if (!contieneEvaluacion(materiaExistente.getEvaluations(), nombreEvaluacion)) {
                    switch (tipoEvaluacion) {
                        case "WRITTEN_EXAM" -> materiaExistente.getEvaluations().add(new WRITTEN(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio, calificacion));
                        case "FINAL_PRACTICAL_WORK" -> materiaExistente.getEvaluations().add(new FINAL_PRACTICAL(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio, calificacion));
                        case "PRACTICAL_WORK" -> materiaExistente.getEvaluations().add(new PRACTICAL(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio, calificacion));
                        case "ORAL_EXAM" -> materiaExistente.getEvaluations().add(new ORAL(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio, calificacion));
                    }
                }
            }
        }
    }

    @Override
    public List<Evaluation> getData() {
        List<Evaluation> evaluacionesPorMateria = new ArrayList<>();
        for (List<COURSE> materias : materiaPorEstudiante.values()) {
            for (COURSE materia : materias) {
                evaluacionesPorMateria.addAll(materia.getEvaluations());
            }
        }
        return evaluacionesPorMateria;
    }

    private boolean contieneEvaluacion(List<Evaluation> evaluaciones, String nombreEvaluacion) {
        for (Evaluation evaluacion : evaluaciones) {
            if (evaluacion.getEvaluationName().equals(nombreEvaluacion)) {
                return true;
            }
        }
        return false;
    }
}
