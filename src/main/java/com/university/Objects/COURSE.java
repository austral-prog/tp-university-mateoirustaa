package com.university.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class COURSE {

    private String nombre;
    private List<Evaluation> evaluaciones;
    public COURSE(String nombre) {
        this.nombre = nombre;
        this.evaluaciones = new ArrayList<>();
    }

    public String getSubject() {
        return nombre;
    }

    public List<Evaluation> getEvaluations() {
        return evaluaciones;
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
