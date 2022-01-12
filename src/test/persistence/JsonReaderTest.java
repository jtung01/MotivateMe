package persistence;

import model.Exercise;
import model.WorkoutList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNoFileFound() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutList wl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    public void testReaderEmptyWorkoutList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkoutList.json");
        try {
            WorkoutList wl = reader.read();
            assertEquals("My workout list", wl.getName());
            assertEquals(0, wl.length());
        } catch (IOException e) {
            fail("Could not read file");
        }
    }

    @Test
    public void testReaderWorkoutList() {
        JsonReader reader = new JsonReader("./data/testReaderWorkoutList.json");
        try {
            WorkoutList wl = reader.read();
            assertEquals("My workout list", wl.getName());
            List<Exercise> exercises = wl.getList();
            assertEquals(2, exercises.size());
            checkExercise("squats", 10, exercises.get(0));
            checkExercise("running", 30, exercises.get(1));
        } catch (IOException e) {
            fail("Could not read file");
        }
    }
}
