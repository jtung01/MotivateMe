package persistence;

import model.Exercise;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExercise(String name, int duration, Exercise ex) {
        assertEquals(name, ex.getName());
        assertEquals(duration, ex.getDuration());
    }
}

