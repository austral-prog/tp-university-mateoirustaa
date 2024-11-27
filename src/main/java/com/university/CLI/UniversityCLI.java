package com.university.CLI;

import com.university.Evaluations.Types.Final;
import com.university.Evaluations.Types.Oral;
import com.university.Evaluations.Types.Practical;
import com.university.Evaluations.Types.Written;
import com.university.Objects.Entity;
import com.university.CLI.Exceptions.NullEntityException;
import com.university.CLI.Exceptions.EntityNotFoundException;
import com.university.CLI.Exceptions.InvalidEntityException;

import com.university.Objects.Evaluation;
import com.university.Objects.Student;
import com.university.Objects.Course;

import java.util.InputMismatchException;
import java.util.Scanner;

// Clase principal que implementa la interfaz CLI
public class UniversityCLI implements CLI {

    // Declaración del objeto Scanner para capturar entradas del usuario
    private final Scanner scanner = new Scanner(System.in);

    // Metodo principal que ejecuta el CLI para interactuar con las interfaces CRUD
    @Override
    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
        System.out.println(" --- University Management App ---");
        boolean running = true;

        // Bucle principal que sigue ejecutándose hasta que el usuario decida salir
        while (running) {
            System.out.println("\nSelect an entity to manage:");
            // Muestra las opciones de entidades disponibles para manejar
            for (int i = 0; i < crudInterfaces.length; i++) {
                System.out.println((i + 1) + ". " + crudInterfaces[i].getIdentifier());
            }
            // Opción para salir del CLI
            System.out.println((crudInterfaces.length + 1) + ". Exit");

            // Captura la opción elegida por el usuario para seleccionar una entidad
            int entityChoice = -1;
            while (entityChoice < 1 || entityChoice > crudInterfaces.length + 1) {
                System.out.print("Choose a number between 1 and " + (crudInterfaces.length + 1) + ": ");
                try {
                    entityChoice = scanner.nextInt();
                    if (entityChoice < 1 || entityChoice > crudInterfaces.length + 1) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    // Manejo de errores en caso de que la entrada no sea un número válido
                    System.out.println("Error: Input is not a valid number. Please enter a number between 1 and " + (crudInterfaces.length + 1) + ".");
                    scanner.nextLine(); // Limpiar el buffer
                }
            }

            // Lógica para ejecutar operaciones CRUD según la elección del usuario
            switch (entityChoice) {
                case 1:
                case 2:
                case 3: {
                    CRUDRepository<?> selectedRepository = crudInterfaces[entityChoice - 1];
                    handleCrudOperations(selectedRepository);
                    break;
                }
                case 4: {
                    running = false; // Sale del CLI
                    System.out.println("Exiting... ");
                    break;
                }
            }
        }
    }

    // Metodo para validar y obtener una entrada numérica dentro de un rango
    private int getValidIntegerInput(int min, int max) {
        int input = -1;
        boolean validInput = false;

        // Bucle para asegurar que el input esté dentro del rango válido
        while (!validInput) {
            try {
                System.out.print("Enter a number between " + min + " and " + max + ": ");
                input = scanner.nextInt();
                scanner.nextLine();

                if (input >= min && input <= max) {
                    validInput = true;
                } else {
                    System.out.println("Error: Number must be between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                // Captura y manejo de error si la entrada no es un número válido
                System.out.println("Error: Input is not a valid number. Please enter a number between " + min + " and " + max + ".");
            }
        }
        return input;
    }

    // Metodo que maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para una entidad
    private <T extends Entity> void handleCrudOperations(CRUDRepository<T> repository) {
        boolean manageEntity = true;
        while (manageEntity) {
            // Muestra las opciones para la operación CRUD
            System.out.println("\nManaging: " + repository.getIdentifier());
            System.out.println("1. Create\n2. Read\n3. Update\n4. Delete\n5. Go back");

            try {
                int operation = getValidIntegerInput(1, 5);

                switch (operation) {
                    case 1:
                        createEntity(repository);
                        break;
                    case 2:
                        readEntity(repository);
                        break;
                    case 3:
                        updateEntity(repository);
                        break;
                    case 4:
                        deleteEntity(repository);
                        break;
                    case 5:
                        manageEntity = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            } catch (InvalidEntityException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    // Metodo para crear una nueva entidad en el repositorio
    private <T extends Entity> void createEntity(CRUDRepository<T> repository) {
        System.out.println("\nCreating new " + repository.getIdentifier() + "...");

        try {
            // Captura el ID de la nueva entidad
            System.out.print("Enter ID for the " + repository.getIdentifier() + ": ");
            int id = getValidIntegerInput(1, Integer.MAX_VALUE);

            // Obtiene los datos de la entidad desde el usuario
            T entity = getEntityDataFromUser(repository.getEntityClass());
            entity.setId(id); // Asigna el ID a la entidad
            repository.create(entity); // Crea la entidad en el repositorio
            System.out.println(repository.getIdentifier() + " with ID " + id + " created successfully.");

        } catch (NullEntityException | InvalidEntityException e) {
            // Captura excepciones específicas para entidades nulas o inválidas
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    // Metodo para leer los detalles de una entidad desde el repositorio
    private <T extends Entity> void readEntity(CRUDRepository<T> repository) {
        System.out.print("Enter the ID of the " + repository.getIdentifier() + " to read: ");

        try {
            // Captura el ID de la entidad a leer
            int id = getValidIntegerInput(1, Integer.MAX_VALUE);
            T entity = repository.read(id); // Lee la entidad desde el repositorio
            System.out.println("Details of " + repository.getIdentifier() + ": " + entity);

        } catch (EntityNotFoundException | InvalidEntityException e) {
            // Captura excepciones cuando no se encuentra la entidad o la entidad es inválida
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    // Metodo para actualizar una entidad en el repositorio
    private <T extends Entity> void updateEntity(CRUDRepository<T> repository) {
        System.out.print("Enter the ID of the " + repository.getIdentifier() + " to update: ");

        try {
            // Captura el ID de la entidad a actualizar
            int id = getValidIntegerInput(1, Integer.MAX_VALUE);
            T entity = repository.read(id); // Lee la entidad actual del repositorio

            System.out.println("Enter new data for the " + repository.getIdentifier() + ":");
            T updatedEntity = getEntityDataFromUser(repository.getEntityClass()); // Obtiene los nuevos datos de la entidad
            updatedEntity.setId(id); // Asigna el ID actualizado a la entidad
            repository.update(id, updatedEntity); // Actualiza la entidad en el repositorio

            System.out.println(repository.getIdentifier() + " with ID " + id + " updated successfully.");

        } catch (EntityNotFoundException | InvalidEntityException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    // Metodo para eliminar una entidad del repositorio
    private <T extends Entity> void deleteEntity(CRUDRepository<T> repository) {
        System.out.print("Enter the ID of the " + repository.getIdentifier() + " to delete: ");

        try {
            // Captura el ID de la entidad a eliminar
            int id = getValidIntegerInput(1, Integer.MAX_VALUE);
            repository.delete(id); // Elimina la entidad del repositorio
            System.out.println(repository.getIdentifier() + " with ID " + id + " deleted successfully.");

        } catch (EntityNotFoundException | InvalidEntityException e) {
            // Captura excepciones cuando no se encuentra la entidad o la entidad es inválida
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    // Metodo para obtener los datos de una entidad del usuario, dependiendo de la clase de entidad
    private <T extends Entity> T getEntityDataFromUser(Class<T> entityClass) {
        while (true) {
            try {
                if (entityClass == Student.class) {
                    // Recoge los datos específicos de un estudiante
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        throw new InvalidEntityException("Student name cannot be empty.");
                    }

                    System.out.print("Enter student email: ");
                    String email = scanner.nextLine().trim();
                    if (email.isEmpty()) {
                        throw new InvalidEntityException("Student email cannot be empty.");
                    }

                    return entityClass.cast(new Student(name, email)); // Crea un estudiante

                } else if (entityClass == Course.class) {
                    // Recoge los datos específicos de un curso
                    System.out.print("Enter course subject: ");
                    String subject = scanner.nextLine().trim();
                    if (subject.isEmpty()) {
                        throw new InvalidEntityException("Course subject cannot be empty.");
                    }

                    Course course = new Course(subject); // Crea un curso
                    boolean addMore = true;
                    while (addMore) {
                        System.out.print("Enter classroom (or type 'done' to finish): ");
                        String classroom = scanner.nextLine().trim();
                        if ("done".equalsIgnoreCase(classroom)) {
                            addMore = false;
                        } else if (!classroom.isEmpty()) {
                            course.addEvaluation(classroom); // Agrega evaluaciones al curso
                        }
                    }
                    return entityClass.cast(course);

                } else if (entityClass == Evaluation.class) {
                    // Recoge los datos específicos de una evaluación
                    System.out.print("Enter student name for evaluation: ");
                    String studentName = scanner.nextLine().trim();
                    if (studentName.isEmpty()) {
                        throw new InvalidEntityException("Student name cannot be empty.");
                    }

                    System.out.print("Enter evaluation subject: ");
                    String subject = scanner.nextLine().trim();
                    if (subject.isEmpty()) {
                        throw new InvalidEntityException("Subject cannot be empty.");
                    }

                    System.out.print("Enter evaluation name: ");
                    String evalName = scanner.nextLine().trim();
                    if (evalName.isEmpty()) {
                        throw new InvalidEntityException("Evaluation name cannot be empty.");
                    }

                    System.out.println("Choose evaluation type:\n1. WrittenExam\n2. OralExam\n3. PracticalWork\n4. FinalPracticalWork");
                    int evalTypeChoice = getValidIntegerInput(1, 4);

                    // Dependiendo de la opción seleccionada, crea el tipo correspondiente de evaluación
                    switch (evalTypeChoice) {
                        case 1 -> {
                            return entityClass.cast(new Written(studentName, subject, "WrittenExam", evalName));
                        }
                        case 2 -> {
                            return entityClass.cast(new Oral(studentName, subject, "OralExam", evalName));
                        }
                        case 3 -> {
                            return entityClass.cast(new Practical(studentName, subject, "PracticalWork", evalName));
                        }
                        case 4 -> {
                            return entityClass.cast(new Final(studentName, subject, "FinalPracticalWork", evalName));
                        }
                        default -> throw new InvalidEntityException("Invalid evaluation type.");
                    }
                }

                // Si no se encuentra el tipo de entidad, lanza una excepción
                throw new InvalidEntityException("Unsupported entity type.");
            } catch (InvalidEntityException e) {
                // En caso de error, muestra un mensaje y vuelve a solicitar la entrada
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }

}
