package com.university.CreatorTests;

import com.university.Creators.CREATOR_2;
import com.university.Objects.Evaluation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Creator2Test {

    @Test
    public void creatorTest() {
        String inputCsv = "src/main/resources/input_2.csv";
        CREATOR_2 creator2 = new CREATOR_2(inputCsv);
        creator2.create();
        List<Evaluation> evaluations = creator2.getData();
        assertEquals(30240, evaluations.size(), "Debe haber 30,618 evaluaciones creadas");
    }
}