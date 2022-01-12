package model;

import org.json.JSONObject;
import persistence.Writer;

// Represents an exercise task with name and duration
public class Exercise implements Writer {
    private final String name;
    private final int duration;

    // REQUIRES: duration > 0
    // EFFECTS: creates an exercise task with the string name and int duration in minutes
    public Exercise(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    // EFFECTS: returns exercise duration
    public int getDuration() {
        return duration;
    }

    // EFFECTS: returns exercise name
    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("duration", duration);
        return json;
    }
}
