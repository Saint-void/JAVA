import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task Management System (Console MVP)
 * 
 * Demonstrates:
 * - Classes and Objects (Task instances)
 * - Collections (ArrayList<Task>)
 * - Methods for modular operations (CRUD + Complete)
 * - User input handling via Scanner
 * - Error handling (try-catch for input validation and bounds checking)
 */
public class Main {
    // Collection to store tasks in memory
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=====================================");
        System.out.println("   Welcome to Task Manager (MVP)    ");
        System.out.println("=====================================");

        while (running) {
            printMenu();
            int choice = getIntInput(scanner, "Choose an option (1-6): ");

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    updateTask(scanner);
                    break;
                case 4:
                    completeTask(scanner);
                    break;
                case 5:
                    deleteTask(scanner);
                    break;
                case 6:
                    System.out.println("Thank you for using Task Manager. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 6.");
            }
            System.out.println(); // Blank line for readability
        }

        scanner.close();
    }

    /**
     * Displays the main menu options.
     */
    private static void printMenu() {
        System.out.println("-------------------------------------");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Update Task");
        System.out.println("4. Complete Task");
        System.out.println("5. Delete Task");
        System.out.println("6. Exit");
        System.out.println("-------------------------------------");
    }

    /**
     * Creates a new task and adds it to the ArrayList.
     */
    private static void addTask(Scanner scanner) {
        System.out.println("\n--- Add New Task ---");
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Task title cannot be empty. Operation canceled.");
            return;
        }

        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter priority (Low, Medium, High): ");
        String priority = scanner.nextLine().trim();
        if (priority.isEmpty()) {
            priority = "Medium"; // sensible default
        }

        Task newTask = new Task(title, description, priority);
        tasks.add(newTask);
        System.out.println("Task added successfully!");
    }

    /**
     * Displays all tasks in the list with 1-based indexing.
     */
    private static void viewTasks() {
        System.out.println("\n--- Task List ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks found. Your task list is empty!");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            // Display as 1-based index for user convenience
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Updates the title, description, or priority of an existing task.
     */
    private static void updateTask(Scanner scanner) {
        System.out.println("\n--- Update Task ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to update.");
            return;
        }

        viewTasks();
        int taskNumber = getIntInput(scanner, "Enter task number to update: ");

        // Basic error handling: check if the index exists
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number. Returning to menu.");
            return;
        }

        Task taskToUpdate = tasks.get(taskNumber - 1);

        System.out.print("Enter new title (press Enter to keep '" + taskToUpdate.getTitle() + "'): ");
        String newTitle = scanner.nextLine().trim();
        if (!newTitle.isEmpty()) {
            taskToUpdate.setTitle(newTitle);
        }

        System.out.print("Enter new description (press Enter to keep current): ");
        String newDesc = scanner.nextLine().trim();
        if (!newDesc.isEmpty()) {
            taskToUpdate.setDescription(newDesc);
        }

        System.out.print("Enter new priority (press Enter to keep '" + taskToUpdate.getPriority() + "'): ");
        String newPriority = scanner.nextLine().trim();
        if (!newPriority.isEmpty()) {
            taskToUpdate.setPriority(newPriority);
        }

        System.out.println("Task updated successfully!");
    }

    /**
     * Marks a selected task as completed.
     */
    private static void completeTask(Scanner scanner) {
        System.out.println("\n--- Complete Task ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to complete.");
            return;
        }

        viewTasks();
        int taskNumber = getIntInput(scanner, "Enter task number to mark as complete: ");

        // Basic error handling: check bounds
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number. Returning to menu.");
            return;
        }

        Task task = tasks.get(taskNumber - 1);
        task.markCompleted();
        System.out.println("Task '" + task.getTitle() + "' marked as complete!");
    }

    /**
     * Deletes a task from the list.
     */
    private static void deleteTask(Scanner scanner) {
        System.out.println("\n--- Delete Task ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to delete.");
            return;
        }

        viewTasks();
        int taskNumber = getIntInput(scanner, "Enter task number to delete: ");

        // Basic error handling: check bounds
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number. Returning to menu.");
            return;
        }

        Task removedTask = tasks.remove(taskNumber - 1);
        System.out.println("Task '" + removedTask.getTitle() + "' was deleted.");
    }

    /**
     * Helper method to safely read an integer from user input.
     * Uses try-catch to prevent program crashes if user enters non-numeric text.
     */
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid whole number.");
            }
        }
    }
}
