package persistence;

import model.Exercise;
import model.WorkoutList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Based off JsonSerializationDemo
// Represents a reader that reads workout list from JSON file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workout list file and returns it,
    // throws IOException if error occurs reading the file
    public WorkoutList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutList(jsonObject);
    }

    // EFFECTS: reads file from jsonwriter or throws IOException
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses workout list from JSON object and returns it
    private WorkoutList parseWorkoutList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkoutList wl = new WorkoutList(name);
        getPoint(wl, jsonObject);
        addExercises(wl, jsonObject);
        return wl;
    }

    // EFFECTS: parses points from JSON object
    private void getPoint(WorkoutList wl, JSONObject jsonObject) {
        int points = jsonObject.getInt("points");
        wl.setPoints(points);
    }

    // MODIFIES: wl
    // EFFECTS: parses exercises from JSON object and adds them to workout list
    private void addExercises(WorkoutList wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(wl, nextExercise);
        }
    }

    // MODIFIES: wl
    // EFFECTS: parses exercise from JSON object and adds it to workout list
    private void addExercise(WorkoutList wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int duration = jsonObject.getInt("duration");
        Exercise ex = new Exercise(name, duration);
        wl.addExercise(ex);
    }
}
