package persistence;

import org.json.JSONObject;

public interface Writer {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
