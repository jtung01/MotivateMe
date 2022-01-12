package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writer;

import java.util.ArrayList;
import java.util.List;


// Represents a list of Exercises
public class WorkoutList implements Writer {
    private final List<Exercise> todo;
    private int points = 0;
    private String name;

    // EFFECTS: creates an empty WorkoutList
    public WorkoutList(String name) {
        this.name = name;
        todo = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: Exercise ex is added to WorkoutList if not already in and returns true
    //          else false
    public boolean addExercise(Exercise ex) {
        if (todo.contains(ex)) {
            return false;
        } else {
            todo.add(ex);
            EventLog.getInstance().logEvent(new Event("Added exercise: " + ex.getName()));
            return true;
        }
    }

    // REQUIRES: Exercise ex is in WorkoutList
    // MODIFIES: this
    // EFFECTS: removes Exercise ex from the WorkoutList
    //          returns confirmation of deleted Exercise
    public String dropExercise(Exercise ex) {
        todo.remove(ex);
        EventLog.getInstance().logEvent(new Event("Dropped exercise: " + ex.getName()));
        return ("Exercise deleted: " + ex.getName());
    }

    // REQUIRES: Exercise ex is in WorkoutList
    // MODIFIES: this
    // EFFECTS: Exercise ex is marked off from WorkoutList and points are added to rewards
    //          returns points
    public int finishExercise(Exercise ex) {
        todo.remove(ex);
        points = points + ex.getDuration();
        EventLog.getInstance().logEvent(new Event("Finished exercise: " + ex.getName()));
        return points;
    }

    // EFFECTS: returns WorkoutList
    public List<Exercise> getList() {
        return todo;
    }

    // EFFECTS: returns points accumulated
    public int getPoints() {
        return points;
    }

    // MODIFIES: this, points
    // EFFECTS: sets points to integer value p
    public void setPoints(int p) {
        points = p;
    }


    // MODIFIES: this
    // EFFECTS: redeems points, subtracts POINTS_FOR_REWARD from points total
    //          returns true if redeemed, else false
    public boolean redeemPoints() {
        int pointsForReward = 420;
        if (pointsForReward <= points) {
            points = points - pointsForReward;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("exercises", todoToJson());
        json.put("points", points);
        return json;
    }

    // EFFECTS: parses through the list to apply jsonarray
    private JSONArray todoToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise ex: todo) {
            jsonArray.put(ex.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns the number of Exercises in the WorkoutList
    public int length() {
        return todo.size();
    }

    // EFFECTS: returns true if Exercise is in WorkoutList else false
    public boolean contains(Exercise ex) {
        return todo.contains(ex);
    }
}