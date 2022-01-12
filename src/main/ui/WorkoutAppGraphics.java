package ui;

import model.EventLog;
import model.Exercise;
import model.WorkoutList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


// Graphics application for workout app
public class WorkoutAppGraphics extends JFrame {
    private static final int width = 800;
    private static final int height = 400;
    private WorkoutList wo;
    private JInternalFrame menu;
    private JTable woList;
    private JDesktopPane desktop;
    private JInternalFrame woL;
    private static final String JSON_STORE = "./data/workoutList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructor sets up button panel and workout list panel
    public WorkoutAppGraphics() {
        wo = new WorkoutList("My workout list");

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        menu = new JInternalFrame("Workout Menu", false, false, false, false);
        menu.setLayout(new BorderLayout());

        woL = new JInternalFrame("Workout List", false, false, false, false);
        woL.setSize(300, height - 100);
        woL.setLocation(450, 0);

        setContentPane(desktop);
        setTitle("Workout App");
        setSize(width, height);

        addButtonPanel();
        addPointPanel();

        menu.pack();
        menu.setVisible(true);
        desktop.add(menu);

        woL.setVisible(true);
        desktop.add(woL);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: adds a table to the desktop
    private void addTable() {
        Map<String, String> exercise = new LinkedHashMap<>();
        exercise.put("**Exercise Name**", "**Exercise Duration**");
        for (Exercise ex : wo.getList()) {
            for (int i = 0; i < wo.length(); i++) {
                exercise.put(ex.getName(), String.valueOf(ex.getDuration()));
            }
        }

        woList = new JTable(toTable(exercise));
        add(woList.getTableHeader(), BorderLayout.PAGE_START);
        add(woList, BorderLayout.EAST);
        woList.setVisible(true);
        woL.add(woList);
    }

    // MODIFIES: this
    // EFFECTS: adds exercise name and duration to the table
    public static TableModel toTable(Map<String,String> map) {
        DefaultTableModel model = new DefaultTableModel(new Object[] {"Exercise Duration", "Exercise Name"}, 0);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            model.addRow(new Object[] {entry.getKey(), entry.getValue()});
        }
        return model;
    }



    // EFFECTS: centres desktop on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


    // MODIFIES: this
    // EFFECTS: adds button controls to internal frame
    private void addButtonPanel() {
        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout(4, 2));
        bp.add(new JButton(new AddExerciseAction()));
        bp.add(new JButton(new DropExerciseAction()));
        bp.add(new JButton(new FinishExerciseAction()));
        bp.add(new JButton(new GetListAction()));
        bp.add(new JButton(new SaveWorkoutAction()));
        bp.add(new JButton(new LoadWorkoutAction()));
        bp.add(new JButton(new EndApp()));

        menu.add(bp, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: adds point buttons onto internal frame
    private void addPointPanel() {
        JPanel pp = new JPanel();
        pp.setLayout(new GridLayout(2, 1));
        pp.add(new JButton(new GetPointsAction()));
        pp.add(new JButton(new RedeemPointsAction()));

        menu.add(pp, BorderLayout.EAST);
    }

    private class EndApp extends AbstractAction {

        EndApp() {
            super("End App");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            LogPrinter lp = new LogPrinter();
            for (String s: lp.printLog(EventLog.getInstance())) {
                System.out.println("\n" + s);
            }
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the add exercise action
    private class AddExerciseAction extends AbstractAction {

        AddExerciseAction() {
            super("Add Exercise");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            doAddExercise();
        }

        // MODIFIES: this
        // EFFECTS: conducts the addExercise method
        private void doAddExercise() {
            String exerciseName = JOptionPane.showInputDialog(null,
                    "Exercise Name?",
                    "Enter exercise name",
                    JOptionPane.QUESTION_MESSAGE);
            String exerciseDuration = JOptionPane.showInputDialog(null,
                    "Exercise Duration?",
                    "Enter Exercise Duration",
                    JOptionPane.QUESTION_MESSAGE);


            int duration = Integer.valueOf(exerciseDuration);

            Exercise ex1 = new Exercise(exerciseName, duration);
            wo.addExercise(ex1);
            JOptionPane.showMessageDialog(null,
                    "Added: " + ex1.getName(),
                    "Added", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the drop exercise action
    private class DropExerciseAction extends AbstractAction {

        DropExerciseAction() {
            super("Drop Exercise");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            doDropExercise();
        }

        // MODIFIES: this
        // EFFECTS: conducts the dropExercise method
        private void doDropExercise() {
            String exerciseName = JOptionPane.showInputDialog(null,
                    "Exercise Name?",
                    "Enter exercise name",
                    JOptionPane.QUESTION_MESSAGE);
            String exerciseDuration = JOptionPane.showInputDialog(null,
                    "Exercise Duration?",
                    "Enter Exercise Duration",
                    JOptionPane.QUESTION_MESSAGE);

            int duration = Integer.valueOf(exerciseDuration);

            for (Exercise ex : wo.getList()) {
                if ((ex.getName().equals(exerciseName)) && (duration == ex.getDuration())) {
                    wo.dropExercise(ex);
                    JOptionPane.showMessageDialog(null,
                            "Dropped: " + ex.getName(),
                            "Dropped", JOptionPane.INFORMATION_MESSAGE);
                    addTable();
                    break;
                }
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: performs the finish exercise action
    private class FinishExerciseAction extends AbstractAction {

        FinishExerciseAction() {
            super("Finish Exercise");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            doFinishExercise();
        }

        // MODIFIES: this
        // EFFECTS: conducts the finishedExercise method
        private void doFinishExercise() {
            String exerciseName = JOptionPane.showInputDialog(null,
                    "Exercise Name?",
                    "Enter exercise name",
                    JOptionPane.QUESTION_MESSAGE);
            String exerciseDuration = JOptionPane.showInputDialog(null,
                    "Exercise Duration?",
                    "Enter Exercise Duration",
                    JOptionPane.QUESTION_MESSAGE);

            int duration = Integer.valueOf(exerciseDuration);

            for (Exercise ex : wo.getList()) {
                if ((ex.getName().equals(exerciseName)) && (duration == ex.getDuration())) {
                    wo.finishExercise(ex);
                    JOptionPane.showMessageDialog(null,
                            "Finished: " + ex.getName(),
                            "Finished", JOptionPane.INFORMATION_MESSAGE);
                    addTable();
                    break;
                }
            }

        }
    }

    // EFFECTS: performs the get points action
    private class GetPointsAction extends AbstractAction {

        GetPointsAction() {
            super("Get Points");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            doGetPoints();
        }

        // EFFECTS: conducts the getPoints method
        private void doGetPoints() {
            JOptionPane.showMessageDialog(null,
                    "Points: " + wo.getPoints(),
                    "Points", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the redeem points action
    private class RedeemPointsAction extends AbstractAction {

        RedeemPointsAction() {
            super("Redeem Points");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            doRedeemPoints();
        }

        // MODIFIES: this
        // EFFECTS: conducts the redeemPoints method
        private void doRedeemPoints() {
            if (wo.getPoints() < 420) {
                JOptionPane.showMessageDialog(null,
                        "Not enough points...",
                        "Not redeemed", JOptionPane.ERROR_MESSAGE);
            } else {
                wo.redeemPoints();
                JOptionPane.showMessageDialog(null,
                        "Enjoy your reward",
                        "Redeemed", JOptionPane.INFORMATION_MESSAGE);
                new ImageGUI();
            }
        }
    }

    // EFFECTS: performs the get list action
    private class GetListAction extends AbstractAction {

        GetListAction() {
            super("Get List");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addTable();
        }
    }

    // EFFECTS: performs the save action
    private class SaveWorkoutAction extends AbstractAction {

        SaveWorkoutAction() {
            super("Save Workout");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveWorkoutList();
        }

        // EFFECTS: saves workout list
        private void saveWorkoutList() {
            try {
                jsonWriter.open();
                jsonWriter.write(wo);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,
                        "Saved " + wo.getName() + " to " + JSON_STORE,
                        "Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Unable to write to file: " + JSON_STORE,
                        "Not saved", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // EFFECTS: performs the load action
    private class LoadWorkoutAction extends AbstractAction {

        LoadWorkoutAction() {
            super("Load Workout");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadWorkoutList();
        }

        // MODIFIES: this
        // EFFECTS: loads previous workout list
        private void loadWorkoutList() {
            try {
                wo = jsonReader.read();
                JOptionPane.showMessageDialog(null,
                        "Loaded " + wo.getName() + " from " + JSON_STORE,
                        "Loaded", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Unable to read from file: " + JSON_STORE,
                        "Not loaded", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // EFFECTS: performs the action that is associated with the button clicked
    private class DesktopFocusAction extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            WorkoutAppGraphics.this.requestFocusInWindow();
        }
    }

    // EFFECTS: starts application
    public static void main(String[] args) {
        new WorkoutAppGraphics();
    }
}
