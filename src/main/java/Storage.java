import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File dataFile;

    public Storage(String dir, String filename) {
        File folder = new File(dir);
        if (!folder.exists()) folder.mkdirs();
        this.dataFile = new File(folder, filename);
    }


    public List<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!dataFile.exists()) return tasks;

        try (Scanner sc = new Scanner(dataFile, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.isEmpty())tasks.add(parseLine(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load tasks: " + e.getMessage(), e);
        }
        return tasks;
    }


    public void save(List<Task> tasks) {
        try (PrintWriter out = new PrintWriter(new FileWriter(dataFile, false))) {
            for (Task t : tasks) out.println(t.toFileString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save tasks: " + e.getMessage(), e);
        }
    }


    private Task parseLine(String s) {
        String[] p = s.split("\\s*\\|\\s*");
        String type = p[0];
        boolean done = "1".equals(p[1]);
        Task t;
        switch (type) {
            case "T": t = new Todo(p[2]); break;
            case "D": t = new Deadline(p[2], p[3]); break;
            case "E": t = new Event(p[2], p[3], p[4]); break;
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
        if (done) t.markAsDone();
        return t;
    }
}
