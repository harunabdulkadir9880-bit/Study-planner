# Personal Study Planner 

A Java console application that manages study tasks. This project was
built to demonstrate several core Java concepts in one place:
**inheritance, polymorphism, custom exception handling, file I/O (text,
binary, and object serialization), and JDBC with MySQL.**

## What This Program Does

You can add three different types of study tasks, see them all listed
together, mark them as completed, or delete them. You choose how your
data gets saved: a plain text file, a binary file, a serialized object
file, or a real MySQL database.

## The 3 Task Types (Inheritance + Polymorphism)

All three task types extend the same parent class, `StudyTask`:

- **DailyTask** — small everyday tasks (has a `subject`)
- **ExamPrep** — studying for an exam (has `examName` and `studyHoursNeeded`)
- **ProjectTask** — bigger group work (has `groupMembers` and `percentComplete`)

Each subclass **overrides** `showDetails()` to print its own extra
fields. When you store all three types together in one list and call
`showDetails()` on each one, Java automatically picks the correct
version for each object — that's **polymorphism** in action. You can
see this happen for real in `displayAllTasks()` in each storage
manager (`TextFileManager`, `BinaryFileManager`, `ObjectFileManager`,
`DatabaseManager`) — they all loop through a list of `StudyTask` and
call `.showDetails()`, with no idea which subclass each one actually
is.

## Custom Exception Handling

`InvalidTaskException` is our own exception class. It gets thrown by
`TaskValidator` when:
- the title is empty, or
- the priority isn't 1, 2, or 3

The Add Task menu catches this with `try/catch/finally` so the program
never crashes from bad input — it just shows a friendly message and
returns to the menu. Try adding a task with priority `5` or an empty
title to see it in action.

## Project Files

| File | What it does |
|---|---|
| `Main.java` | The entry point. Shows the menu and connects everything together. |
| `StudyTask.java` | The superclass — shared fields (title, due date, priority, completed). |
| `DailyTask.java` | Subclass — adds a `subject` field. |
| `ExamPrep.java` | Subclass — adds `examName` and `studyHoursNeeded`. |
| `ProjectTask.java` | Subclass — adds `groupMembers` and `percentComplete`. |
| `InvalidTaskException.java` | Our custom exception class. |
| `TaskValidator.java` | Checks task data and throws `InvalidTaskException` if something's wrong. |
| `FileHelper.java` | Creates folders/files automatically and shows file properties. |
| `TextFileManager.java` | Add/Display/Delete/Mark-Completed using `Scanner` and `PrintWriter` (text file). |
| `BinaryFileManager.java` | Same operations using `DataInputStream` and `DataOutputStream` (binary file). |
| `ObjectFileManager.java` | Same operations using `ObjectInputStream` and `ObjectOutputStream` (object serialization). |
| `DBHelper.java` | Connects to the MySQL database and creates the `tasks` table if needed (JDBC). |
| `DatabaseManager.java` | Same operations using JDBC (`PreparedStatement`, `ResultSet`) against MySQL. |

## How to Run It (Without the Database Option)

If you just want to test text, binary, and object file storage:

1. Put all the `.java` files in the same folder.
2. Open a terminal in that folder.
3. Compile everything:
   ```
   javac *.java
   ```
4. Run the program:
   ```
   java Main
   ```

The program will automatically create a `data` folder the first time
it runs. The database option will print a connection error if you
haven't set up MySQL yet (see below), but everything else still works
fine.

## JDBC Setup (Required for the Database Option)

The database storage option (#4) uses **MySQL** through JDBC.

## Menu Options

```
1. Add Task
2. Display All Tasks
3. Delete Task
4. Mark Task as Completed
5. Show File Properties
6. Exit
```

For options 1–4, you'll be asked which storage type to use: text
file, binary file, object file, or database. Each storage type keeps
its own separate copy of the data — a task added to the text file
won't show up in the database (or vice versa) unless you add it there
too.

## Notes

- Priority must be 1 (Low), 2 (Medium), or 3 (High) — anything else
  triggers our custom `InvalidTaskException`.
- Deleting and marking complete both work by matching the task's
  exact title.
- All file and database operations are wrapped in try/catch so the
  program won't crash if something goes wrong.
