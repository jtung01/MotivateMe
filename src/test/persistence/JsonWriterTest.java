package persistence;

import model.Exercise;
import model.WorkoutList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            WorkoutList wl = new WorkoutList("My workout list");
            JsonWriter writer = new JsonWriter(".data/my\0illegal:fileName.json");
            writer.open();
            fail("Exception was expected");
        } catch (IOException e) {
        }
    }

    @Test
    public void testWriterEmptyWorkoutList() {
        try {
            WorkoutList wl = new WorkoutList("My workout list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkoutList.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutList.json");
            wl = reader.read();
            assertEquals("My workout list", wl.getName());
            assertEquals(0, wl.length());
        } catch (IOException e) {
            fail("Exception was not to be thrown");
        }
    }

    @Test
    public void testWriterWorkoutList() {
        try {
            WorkoutList wl = new WorkoutList("My workout list");
            wl.addExercise(new Exercise("running", 30));
            wl.addExercise(new Exercise("squats", 10));
            JsonWriter writer = new JsonWriter("./data/testWriterWorkoutList.json");
            writer.open();
            writer.write(wl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWorkoutList.json");
            wl = reader.read();
            List<Exercise> exercises = wl.getList();
            assertEquals("My workout list", wl.getName());
            assertEquals(2, exercises.size());
            checkExercise("running", 30, exercises.get(0));
            checkExercise("squats", 10, exercises.get(1));

        } catch (IOException e) {
            fail("Exception was not to be thrown");
        }
    }
}
