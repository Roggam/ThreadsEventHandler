import java.util.HashMap;
import java.util.Map;

public class EventTracker implements Tracker {

    private static EventTracker INSTANCE = new EventTracker();

    private Map<String, Integer> tracker;

    private EventTracker() {
        this.tracker = new HashMap<>();
    }

    synchronized public static EventTracker getInstance() {
        return INSTANCE;
    }

    synchronized public void push(String message) {
        Integer count = tracker.containsKey(message) ? tracker.get(message) : 0;
        count++;
        tracker.put(message, count);
    }

    synchronized public Boolean has(String message) {
        if (tracker.containsKey(message) && tracker.get(message) > 0) {
            return true;
        }
        return false;
    }

    synchronized public void handle(String message, EventHandler e) {
        e.handle();
        Integer count = tracker.containsKey(message) ? tracker.get(message) : 0;
        count--;
        tracker.put(message, count);

    }

    public Map<String, Integer> tracker() {
        return this.tracker;
    }

    // Do not use this. This constructor is for tests only
    // Using it breaks the singleton class
    EventTracker(Map<String, Integer> tracker) {
        this.tracker = tracker;
    }


}
