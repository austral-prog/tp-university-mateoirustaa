package com.university.CLI.Repositories;

import com.university.Objects.Evaluation;
import com.university.CLI.CRUDRepository;
import com.university.CLI.Exceptions.NullEntityException;
import java.util.HashMap;
import java.util.Map;

public class EvaluationRepo implements CRUDRepository<Evaluation> {
    private final Map<Integer, Evaluation> evaluations = new HashMap<>();

    @Override
    public void create(Evaluation evaluation) {
        if (evaluation == null) {
            throw new NullEntityException("The evaluation cannot be null.");
        }
        int id = evaluations.size() + 1; // Auto-generate ID
        evaluation.setId(id);
        evaluations.put(id, evaluation);
    }

    @Override
    public Evaluation read(int id) {
        return evaluations.get(id);
    }

    @Override
    public void update(int id, Evaluation evaluation) {
        if (evaluation == null) {
            throw new NullEntityException("The evaluation cannot be null.");
        }
        if (evaluations.containsKey(id)) {
            evaluations.put(id, evaluation);
        } else {
            throw new IllegalArgumentException("Evaluation with the specified ID does not exist.");
        }
    }

    @Override
    public void delete(int id) {
        evaluations.remove(id);
    }

    @Override
    public String getIdentifier() {
        return "Evaluation";
    }

    @Override
    public Class<Evaluation> getEntityClass() {
        return Evaluation.class;
    }
}
