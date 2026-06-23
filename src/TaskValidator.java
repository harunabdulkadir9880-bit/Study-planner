public class TaskValidator {
public static void validate(String title, int priority) throws InvalidtaskException {
if (title == null || title.trim().isEmpty()) {
throw new InvalidtaskException("Title cannot be empty.");
}
if (priority < 1 || priority > 3) {
throw new InvalidtaskException("Priority must be 1 (low), 2 (medium), or 3 (high).");
}
}
}
