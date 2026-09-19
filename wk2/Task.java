/**
 * Represents a single task in the Task Management System.
 * Demonstrates Object-Oriented Programming (OOP) concepts:
 * - Encapsulation (private fields with public getters/setters)
 * - Constructors to initialize state
 * - Method overriding (toString)
 */
public class Task {
    private String title;
    private String description;
    private String priority;     // e.g., Low, Medium, High
    private boolean isCompleted;

    /**
     * Constructor to initialize a new task.
     * Newly created tasks default to not completed (false).
     */
    public Task(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    // --- Getters and Setters ---

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    /**
     * Helper method to mark the task as complete.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Returns a formatted string representation of the task.
     * Example: [X] Buy groceries (Priority: High) - Milk, eggs, bread
     */
    @Override
    public String toString() {
        String statusMarker = isCompleted ? "[X]" : "[ ]";
        return statusMarker + " " + title + " (Priority: " + priority + ") - " + description;
    }
}
