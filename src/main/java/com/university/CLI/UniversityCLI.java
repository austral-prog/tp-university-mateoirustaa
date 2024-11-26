package com.university.CLI;

import com.university.Objects.Course;
import com.university.Objects.Evaluation;
import com.university.Objects.Student;

import java.util.List;
import java.util.Scanner;

public class UniversityCLI implements CLI {

    private final Scanner scanner = new Scanner(System.in);

    public UniversityCLI(List<String> student, List<Integer> courseCountOfStudents) {
    }

    @Override
    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
        CRUDRepository<Student> StudentRepo = (CRUDRepository<Student>) crudInterfaces[0];
        CRUDRepository<Course> CourseRepo = (CRUDRepository<Course>) crudInterfaces[1];
        CRUDRepository<Evaluation> EvaluationRepo = (CRUDRepository<Evaluation>) crudInterfaces[2];

        while (true) {
            System.out.println("Managing repositories...");
            System.out.println("[1] Manage Students");
            System.out.println("[2] Manage Courses");
            System.out.println("[3] Manage Evaluations");
            System.out.println("[4] Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> handleCRUDOperations(StudentRepo);
                case 2 -> handleCRUDOperations(CourseRepo);
                case 3 -> handleCRUDOperations(EvaluationRepo);
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private <T extends Entity> void handleCRUDOperations(CRUDRepository<T> repository) {
        System.out.println("Choose an operation: [1] Create, [2] Read, [3] Update, [4] Delete");
        int operation = scanner.nextInt();
        scanner.nextLine();

        switch (operation) {
            case 1 -> createEntity(repository);
            case 2 -> readEntity(repository);
            case 3 -> updateEntity(repository);
            case 4 -> deleteEntity(repository);
            default -> System.out.println("Invalid operation.");
        }
    }

    private <T extends Entity> void createEntity(CRUDRepository<T> repository) {
        if (repository.getIdentifier().equals("Student")) {
            System.out.println("Creating a new student...");
            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine();
            System.out.print("Enter student email: ");
            String email = scanner.nextLine();
            System.out.print("Enter an id:");
            String id = String.valueOf(scanner.nextInt());
            Student student = new Student(fullName, email, id);
            repository.create((T) student);
            System.out.println("Student created successfully.");
        } else if (repository.getIdentifier().equals("Course")) {
            System.out.println("Creating a new course...");
            System.out.print("Enter course name: ");
            String courseName = scanner.nextLine();
            Course course = new Course(courseName);
            repository.create((T) course);
            System.out.println("Course created successfully.");
        } else if (repository.getIdentifier().equals("Evaluation")) {
            System.out.println("Creating a new evaluation...");
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();
            System.out.print("Enter subject: ");
            String subject = scanner.nextLine();
            System.out.print("Enter evaluation name: ");
            String evaluationName = scanner.nextLine();
            System.out.print("Enter exercise name: ");
            String exerciseName = scanner.nextLine();
            System.out.print("Enter grade: ");
            double grade = scanner.nextDouble();
            scanner.nextLine();
            Evaluation evaluation = new Evaluation(studentName, subject, evaluationName, exerciseName, grade) {
                @Override
                public String getType() {
                    return "Exam";
                }

                @Override
                public String getEvaluationKind() {
                    return "Final";
                }
            };
            repository.create((T) evaluation);
            System.out.println("Evaluation created successfully.");
        } else {
            System.out.println("Entity type not supported for creation.");
        }
    }

    private <T extends Entity> void readEntity(CRUDRepository<T> repository) {
        System.out.println("Enter the ID of the entity to read:");
        int id = scanner.nextInt();
        scanner.nextLine();

        T entity = repository.read(id);
        if (entity != null) {
            System.out.println(entity.toString());
        } else {
            System.out.println("Entity not found.");
        }
    }

    private <T extends Entity> void updateEntity(CRUDRepository<T> repository) {
        System.out.println("Enter the ID of the entity to update:");
        int id = scanner.nextInt();
        scanner.nextLine();

        T entity = repository.read(id);
        if (entity != null) {
            if (entity instanceof Student) {
                Student student = (Student) entity;
                System.out.print("Enter new full name: ");
                student.FullName = scanner.nextLine();
                System.out.print("Enter new email: ");
                student.student_Email = scanner.nextLine();
            } else if (entity instanceof Course) {
                Course course = (Course) entity;
                System.out.print("Enter new course name: ");
                course.nombre = scanner.nextLine();
            } else if (entity instanceof Evaluation) {
                Evaluation evaluation = (Evaluation) entity;
                System.out.print("Enter new grade: ");
                evaluation.grade = scanner.nextDouble();
                scanner.nextLine();
            }
            repository.update(id, entity);
            System.out.println("Entity updated successfully.");
        } else {
            System.out.println("Entity not found.");
        }
    }

    private <T extends Entity> void deleteEntity(CRUDRepository<T> repository) {
        System.out.println("Enter the ID of the entity to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();

        T entity = repository.read(id);
        if (entity != null) {
            repository.delete(id);
            System.out.println("Entity deleted successfully.");
        } else {
            System.out.println("Entity not found.");
        }
    }
}
