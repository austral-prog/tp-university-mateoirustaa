package com.university.Course;

import com.university.Evaluations.Evaluation;
import com.university.Evaluations.Types.FINAL_PRACTICAL;
import com.university.Evaluations.Types.ORAL;
import com.university.Evaluations.Types.PRACTICAL;
import com.university.Evaluations.Types.WRITTEN;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class COURSE {

    private String nombre;
    private List<String> aulas;
    private List<Evaluation> evaluaciones;
    private List<WRITTEN> examenesEscritos;
    private List<ORAL> examenesOrales;
    private List<PRACTICAL> trabajosPracticos;
    private List<FINAL_PRACTICAL> trabajosPracticosFinales;

    public COURSE(String nombre) {
        this.nombre = nombre;
        this.aulas = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
        this.examenesEscritos = new ArrayList<>();
        this.examenesOrales = new ArrayList<>();
        this.trabajosPracticos = new ArrayList<>();
        this.trabajosPracticosFinales = new ArrayList<>();
    }

    public String getSubject() {
        return nombre;
    }

    public List<Evaluation> getEvaluations() {
        return evaluaciones;
    }

    public List<String> getEvaluationNames() {
        List<String> nombres = new ArrayList<>();
        for (Evaluation evaluacion : evaluaciones) {
            nombres.add(evaluacion.getSubject());
        }
        return nombres;
    }

    public void addEvaluation(Evaluation evaluacion) {
        evaluaciones.add(evaluacion);
        if (evaluacion instanceof WRITTEN) {
            examenesEscritos.add((WRITTEN) evaluacion);
        } else if (evaluacion instanceof ORAL) {
            examenesOrales.add((ORAL) evaluacion);
        } else if (evaluacion instanceof PRACTICAL) {
            trabajosPracticos.add((PRACTICAL) evaluacion);
        } else if (evaluacion instanceof FINAL_PRACTICAL) {
            trabajosPracticosFinales.add((FINAL_PRACTICAL) evaluacion);
        }
    }

    public double getFinalGrade() {
        divideEvaluationsByType(evaluaciones);

        double calificacionTotal = 0.0;
        int contador = 0;

        if (!examenesEscritos.isEmpty()) {
            double suma = 0.0;
            for (WRITTEN examen : examenesEscritos) {
                suma += examen.getGrade();
            }
            calificacionTotal += suma / examenesEscritos.size();
            contador++;
        }

        if (!examenesOrales.isEmpty()) {
            calificacionTotal += examenesOrales.get(0).getGrade();
            contador++;
        }

        if (!trabajosPracticos.isEmpty()) {
            calificacionTotal += trabajosPracticos.get(trabajosPracticos.size() - 1).getGrade();
            contador++;
        }

        if (!trabajosPracticosFinales.isEmpty()) {
            calificacionTotal += trabajosPracticosFinales.get(trabajosPracticosFinales.size() - 1).getGrade();
            contador++;
        }

        return contador > 0 ? calificacionTotal / contador : 0.0;
    }

    public void divideEvaluationsByType(List<Evaluation> todasLasEvaluaciones) {
        for (Evaluation evaluacion : todasLasEvaluaciones) {
            if (evaluacion instanceof WRITTEN) {
                examenesEscritos.add((WRITTEN) evaluacion);
            } else if (evaluacion instanceof ORAL) {
                examenesOrales.add((ORAL) evaluacion);
            } else if (evaluacion instanceof PRACTICAL) {
                trabajosPracticos.add((PRACTICAL) evaluacion);
            } else if (evaluacion instanceof FINAL_PRACTICAL) {
                trabajosPracticosFinales.add((FINAL_PRACTICAL) evaluacion);
            }
        }
    }

    public List<Double> getGrades() {
        List<Double> calificaciones = new ArrayList<>();
        for (Evaluation evaluacion : evaluaciones) {
            calificaciones.add(evaluacion.getGrade());
        }
        return calificaciones;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        COURSE materia = (COURSE) obj;
        return Objects.equals(nombre, materia.nombre);
    }
}
