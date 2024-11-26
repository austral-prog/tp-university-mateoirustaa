package com.university.CreatorTests;

import com.university.Creators.CREATOR_1;
import org.junit.jupiter.api.Test;
import static com.university.Maps.materiaPorEstudiante;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Creator1Test {

    @Test
    public void creatorTest() {
        String inputCsv = "src/main/resources/input.csv";
        CREATOR_1 creator = new CREATOR_1(inputCsv);

        creator.create();

        assertEquals(400, creator.getData().size(), "Debe haber 400 estudiantes creados");
        assertEquals(400, materiaPorEstudiante.size(), "Debe haber 20 materias distintas");
    }
}