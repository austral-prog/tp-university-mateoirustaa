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

import static com.university.Maps.materiaPorEstudiante;
import static com.university.Maps.estudiantePorNombre;

public class Creator2 implements Creator<Evaluation> {

    private String archivo;
    private int idCounter = 1;  // Contador para generar ID enteros secuenciales

    public Creator2(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void create() {
        CsvReader lectorCsv = new CsvReader(archivo);
        lectorCsv.read(",");
        List<String[]> todosLosDatos = lectorCsv.getData();

        for (String[] dato : todosLosDatos) {
            if (dato.length == 0 || dato[0].trim().isEmpty()) {
                // Si la línea está vacía, la ignoramos
                continue;
            }

            // Verificar si hay el número de columnas esperado
            if (dato.length != 6) {
                System.out.println("Formato de línea inválido: " + String.join(",", dato));
                continue;
            }

            String nombreEstudiante = dato[0];
            String nombreMateria = dato[1];
            String tipoEvaluacion = dato[2];
            String nombreEvaluacion = dato[3];
            String nombreEjercicio = dato[4];
            double calificacion = 0;

            // Validar y parsear la calificación
            try {
                calificacion = parseCalificacion(dato[5]);
            } catch (NumberFormatException e) {
                System.out.println("Formato de calificación inválido: " + dato[5]);
                continue; // Saltar esta fila si la calificación no es válida
            }

            // Buscar o crear el estudiante
            Student estudiante = estudiantePorNombre.get(nombreEstudiante);
            if (estudiante == null) {
                // Si el estudiante no existe, lo creamos con un ID numérico secuencial
                estudiante = new Student(nombreEstudiante, "email");
                estudiantePorNombre.put(nombreEstudiante, estudiante);
            }

            // Buscar o crear la lista de materias del estudiante
            List<Course> materias = materiaPorEstudiante.computeIfAbsent(estudiante, k -> new ArrayList<>());

            // Buscar la materia
            Course materiaExistente = null;
            for (Course c : materias) {
                if (c.getSubject().equals(nombreMateria)) {
                    materiaExistente = c;
                    break;
                }
            }

            // Si la materia no existe, la creamos
            if (materiaExistente == null) {
                materiaExistente = new Course(nombreMateria);
                materias.add(materiaExistente);
            }

            // Verificar si la evaluación ya existe
            if (!contieneEvaluacion(materiaExistente.getEvaluations(), nombreEvaluacion)) {
                // Crear la evaluación correspondiente según el tipo
                switch (tipoEvaluacion) {
                    case "WRITTEN_EXAM" -> materiaExistente.getEvaluations().add(new Written(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio));
                    case "FINAL_PRACTICAL_WORK" -> materiaExistente.getEvaluations().add(new Final(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio));
                    case "PRACTICAL_WORK" -> materiaExistente.getEvaluations().add(new Practical(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio));
                    case "ORAL_EXAM" -> materiaExistente.getEvaluations().add(new Oral(nombreEstudiante, nombreMateria, nombreEvaluacion, nombreEjercicio));
                    default -> System.out.println("Tipo de evaluación desconocido: " + tipoEvaluacion);
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

    // Método auxiliar para validar y parsear la calificación
    private double parseCalificacion(String calificacionStr) throws NumberFormatException {
        if (!calificacionStr.matches("^[0-9]*\\.?[0-9]+$")) { // Verifica que sea un número válido
            throw new NumberFormatException("Calificación no válida: " + calificacionStr);
        }
        return Double.parseDouble(calificacionStr);
    }

    // Método para verificar si ya existe una evaluación con el mismo nombre
    private boolean contieneEvaluacion(List<Evaluation> evaluaciones, String nombreEvaluacion) {
        for (Evaluation evaluacion : evaluaciones) {
            if (evaluacion.getEvaluationName().equals(nombreEvaluacion)) {
                return true;
            }
        }
        return false;
    }
}
