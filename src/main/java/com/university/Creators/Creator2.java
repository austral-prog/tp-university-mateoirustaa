package com.university.Creators;

import com.university.CSV.CsvReader;
import com.university.Objects.Course;
import com.university.Evaluations.Types.Final;
import com.university.Evaluations.Types.Oral;
import com.university.Evaluations.Types.Practical;
import com.university.Evaluations.Types.Written;
import com.university.Objects.Evaluation;
import com.university.Objects.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.university.Maps.materiaPorEstudiante;
import static com.university.Maps.estudiantePorNombre;

public class Creator2 implements Creator<Evaluation> {

    private String archivo;

    public Creator2(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void create() {
        CsvReader lectorCsv = new CsvReader(archivo);
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

                Student estudiante = estudiantePorNombre.get(nombreEstudiante);
                if (estudiante == null) {
                    String estudianteId = UUID.randomUUID().toString();
                    estudiante = new Student(nombreEstudiante, "email", estudianteId);
                    estudiantePorNombre.put(nombreEstudiante, estudiante);
                }

                List<Course> materias = materiaPorEstudiante.computeIfAbsent(estudiante, k -> new ArrayList<>());

                Course materiaExistente = null;
                for (Course c : materias) {
                    if (c.getSubject().equals(nombreMateria)) {
                        materiaExistente = c;
                        break;
                    }
                }

                if (materiaExistente == null) {
                    materiaExistente = new Course(nombreMateria);
                    materias.add(materiaExistente);
                }

                if (!contieneEvaluacion(materiaExistente.getEvaluations(), nombreEvaluacion)) {
                    switch (tipoEvaluacion) {
                        case "WRITTEN_EXAM" -> materiaExistente.getEvaluations().add(new Written(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio, calificacion));
                        case "FINAL_PRACTICAL_WORK" -> materiaExistente.getEvaluations().add(new Final(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio, calificacion));
                        case "PRACTICAL_WORK" -> materiaExistente.getEvaluations().add(new Practical(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio, calificacion));
                        case "ORAL_EXAM" -> materiaExistente.getEvaluations().add(new Oral(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio, calificacion));
                    }
                }
            }
        }
    }

    @Override
    public List<Evaluation> getData() {
        List<Evaluation> evaluacionesPorMateria = new ArrayList<>();
        for (List<Course> materias : materiaPorEstudiante.values()) {
            for (Course materia : materias) {
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
