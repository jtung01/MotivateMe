package ui;

import model.Event;
import model.EventLog;

import java.util.ArrayList;
import java.util.List;

// A class that parses through eventlog to take out events as strings
public class LogPrinter {

    // MODIFIES: this
    // EFFECTS: adds the events as a string to a list from eventlog and returns the list of events
    public List<String> printLog(EventLog el) {
        List<String> events = new ArrayList<>();
        for (Event e: el) {
            events.add(e.toString());
        }
        return events;
    }
}
