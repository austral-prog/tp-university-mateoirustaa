package com.university.CreatorTests;

import com.university.Creators.Creator3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Creator3Test {

    @Test
    void testCreate() {
        Creator3 creator = new Creator3("src/main/resources/input_3.csv");

        creator.create();

        assertNotNull(creator.getData(), "Los datos no deben ser null");
        assertFalse(creator.getData().isEmpty(), "Los resultados no deben estar vacíos");

        assertNotNull(creator.getEvaluations(), "Las evaluaciones no deben ser null");
        assertFalse(creator.getEvaluations().isEmpty(), "Las evaluaciones no deben estar vacías");

        assertEquals(33, creator.getData().size(), "Debe haber 2 entradas procesadas");
    }
}
