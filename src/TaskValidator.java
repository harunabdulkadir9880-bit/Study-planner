public class TaskValidator {
public static void validate(String title, int priority) throws InvalidTaskException {
if (title == null || title.trim().isEmpty()) {
throw new InvalidTaskException("Title cannot be empty.");
}
if (priority < 1 || priority > 3) {
throw new InvalidTaskException("Priority must be 1 (low), 2 (medium), or 3 (high).");
}
}
}
