package com.university.CLI;


import com.university.Objects.STUDENT;

import java.util.List;
import java.util.Scanner;

public class UniversityCLI implements CLI {
    private final Scanner scanner = new Scanner(System.in);
    private List<String> studentNames;
    private List<Integer> courseCounts;


    public UniversityCLI(List<String> studentNames, List<Integer> courseCounts) {
        this.studentNames = studentNames;
        this.courseCounts = courseCounts;
    }

    @Override
    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
        CRUDRepository<STUDENT> studentRepo = (CRUDRepository<STUDENT>) crudInterfaces[0];

        while (true) {
            System.out.println("Managing Student repository...");
            handleCRUDOperations(studentRepo);
        }
    }

    private <T extends com.university.CLI.Entity> void handleCRUDOperations(CRUDRepository<T> repository) {
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

    private <T extends com.university.CLI.Entity> void createEntity(CRUDRepository<T> repository) {
        if (repository.getIdentifier().equals("Student")) {
            System.out.println("Creating a new student...");


            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter number of courses: ");
            int courseCount = scanner.nextInt();
            scanner.nextLine();


            studentNames.add(name);
            courseCounts.add(courseCount);

            System.out.println("Student created successfully.");
        } else {
            System.out.println("Entity type not supported for creation.");
        }
    }

    private <T extends com.university.CLI.Entity> void readEntity(CRUDRepository<T> repository) {
        if (repository.getEntityClass().equals(STUDENT.class)) {
            System.out.println("Displaying all students:");
            int j = 0;
            for (int i = 0; i < studentNames.size(); i++) {
                System.out.println("id:" + j + ", Student: " + studentNames.get(i) + ", Course Count: " + courseCounts.get(i));
                j++;
            }
        } else {
            System.out.println("Entity type not supported for reading.");
        }
    }

    private <T extends com.university.CLI.Entity> void updateEntity(CRUDRepository<T> repository) {
        if (repository.getIdentifier().equals("Student")) {
            System.out.println("Enter the index of the student to update: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 0 && index < studentNames.size()) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new course count: ");
                int newCourseCount = scanner.nextInt();
                scanner.nextLine();


                studentNames.set(index, newName);
                courseCounts.set(index, newCourseCount);

                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Invalid student index.");
            }
        } else {
            System.out.println("Entity type not supported for updating.");
        }
    }

    private <T extends com.university.CLI.Entity> void deleteEntity(CRUDRepository<T> repository) {
        if (repository.getIdentifier().equals("Student")) {
            System.out.println("Enter the index of the student to delete: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 0 && index < studentNames.size()) {

                studentNames.remove(index);
                courseCounts.remove(index);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Invalid student index.");
            }
        } else {
            System.out.println("Entity type not supported for deletion.");
        }
    }
}