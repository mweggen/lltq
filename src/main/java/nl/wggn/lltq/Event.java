package nl.wggn.lltq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michiel on 12-12-2016.
 */
public class Event {

    private static final List<List<Event>> events = new ArrayList<>(40);
    static {
        for (int i = 0; i < 40; i++) {
            events.add(new ArrayList<>());
        }
    }

    private static final Event W1_CHARLOTTE_ARRIVES = new Event.Builder("Charlotte Arrives")
            .week(0)
            .addEffect(Mood.CHEERFUL, +1).build();

    private final String name;
    private final Map<Mood, Integer> moodEffectMap;

    private Event(Builder builder) {
        this.name = builder.name;
        this.moodEffectMap = builder.moodEffectMap;
        events.get(builder.week).add(this);
    }

    public void applyEffects(MoodValues moodValues) {
        moodValues.applyEffects(moodEffectMap);
    }

    @Override
    public String toString() {
        return name;
    }

    private static class Builder {
        private String name;
        private Integer week = null;
        private Map<Mood, Integer> moodEffectMap = new HashMap<>();
        public Builder(String name) {
            this.name = name;
        }
        public Builder week(int week) {
            this.week = week;
            return this;
        }
        public Builder addEffect(Mood mood, int effect) {
            moodEffectMap.put(mood, effect);
            return this;
        }
        public Event build() {
            return new Event(this);
        }
    }

    public static List<Event> getEvents(int week) {
        return events.get(week);
    }
}
