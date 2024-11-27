package com.university.CLI;

import com.university.CLI.Repositories.CourseRepo;
import com.university.CLI.Repositories.EvaluationRepo;
import com.university.CLI.Repositories.StudentRepo;
import com.university.Objects.Course;
import com.university.Objects.Evaluation;
import com.university.Objects.Student;

public class RunnerCLI {
    public static void main(String[] args) {
        CRUDRepository<Student> studentRepo = new StudentRepo();
        CRUDRepository<Course> courseRepo = new CourseRepo();
        CRUDRepository<Evaluation> evaluationRepo = new EvaluationRepo();
        CLI cli = new UniversityCLI();
        cli.runCLI(new CRUDRepository[]{studentRepo, courseRepo, evaluationRepo});
    }
}