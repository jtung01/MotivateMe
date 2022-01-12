package persistence;

import model.WorkoutList;
import org.json.JSONObject;
import java.io.*;

// Based off JsonSerializationDemo
// Writer that writes JSON representation of a workout list to file
public class JsonWriter {
    private PrintWriter writer;
    private String saveFile;

    // EFFECTS: constructor for writer to write into saveFile file
    public JsonWriter(String saveFile) {
        this.saveFile = saveFile;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer and throws FileNotFoundException if saveFile cannot open for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(saveFile));
    }

    // MODIFIES: this
    // EFFECTS writes JSON of workout list to a file
    public void write(WorkoutList wl) {
        JSONObject json = wl.toJson();
        saveToFile(json.toString(2));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
