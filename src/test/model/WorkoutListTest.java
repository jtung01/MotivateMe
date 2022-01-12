package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WorkoutListTest {
    public WorkoutList wol1;
    public Exercise e1;
    public Exercise e2;
    public int points1 = 420;
    public int points2 = 1000;
    public int points3 = 400;
    public List<Exercise> wList;

    @BeforeEach
    public void runBefore() {
        wol1 = new WorkoutList("My workout list");
        e1 = new Exercise("Squats", 60);
        e2 = new Exercise("Sprints", 120);
    }

    @Test
    public void testAddExerciseNotInList() {
        assertTrue(wol1.addExercise(e1));
        assertTrue(wol1.addExercise(e2));
        assertEquals(2, wol1.length());
    }

    @Test
    public void testAddExerciseInList() {
        assertTrue(wol1.addExercise(e1));
        assertFalse(wol1.addExercise(e1));
        assertEquals(1, wol1.length());
    }

    @Test
    public void testDropExercise() {
        wol1.addExercise(e1);
        wol1.addExercise(e2);

        assertEquals("Exercise deleted: Squats", wol1.dropExercise(e1));
        assertEquals(1, wol1.length());
        assertTrue(wol1.contains(e2));
        assertFalse(wol1.contains(e1));
    }

    @Test
    public void testFinishExercise() {
        wol1.addExercise(e1);
        wol1.addExercise(e2);
        assertEquals(60, wol1.finishExercise(e1));

        assertEquals(1, wol1.length());
        assertFalse(wol1.contains(e1));

        assertEquals(180, wol1.finishExercise(e2));
    }

    @Test
    public void testRedeemPointsExactlyEnough() {
        Exercise e420 = new Exercise("situps", 420);
        wol1.addExercise(e420);
        wol1.finishExercise(e420);
        assertTrue(wol1.redeemPoints());
    }

    @Test
    public void testRedeemPointsEnough() {
        Exercise e1000 = new Exercise("situps", 1000);
        wol1.addExercise(e1000);
        wol1.finishExercise(e1000);
        assertTrue(wol1.redeemPoints());
    }

    @Test
    public void testRedeemPointsNotEnough() {
        Exercise e410 = new Exercise("situps", 410);
        wol1.addExercise(e410);
        wol1.finishExercise(e410);
        assertFalse(wol1.redeemPoints());
    }

    @Test
    public void testGetPoints() {
        assertEquals(0,wol1.getPoints());
        wol1.addExercise(e2);
        wol1.finishExercise(e2);
        assertEquals(120, wol1.getPoints());
    }

    @Test
    public void testGetList() {
        wList = new ArrayList<>();
        wList.add(e1);
        wol1.addExercise(e1);

        assertEquals(wList, wol1.getList());
    }
}
