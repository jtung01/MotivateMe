package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Based on AlarmSystem.model.EventLog
// A log of events when exercises are added, dropped, or finished
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: constructs an eventlog
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: gets and creates an instance of an event if it does not already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds event to log
    public void logEvent(Event e) {
        events.add(e);
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
