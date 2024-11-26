package com.university.Objects;

import com.university.CLI.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course implements Entity {

    private int id;
    public String nombre;
    private List<Evaluation> evaluaciones;

    public Course(String nombre) {
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
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course materia = (Course) obj;
        return Objects.equals(nombre, materia.nombre);
    }

    @Override
    public String toString() {
        return "COURSE{id=" + id + ", nombre='" + nombre + "'}";
    }
}
