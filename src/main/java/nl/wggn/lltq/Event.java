package nl.wggn.lltq;

import java.util.*;
import java.util.function.Predicate;

import static nl.wggn.lltq.Mood.*;

/**
 * Created by Michiel on 12-12-2016.
 */
public class Event {

    private static final List<List<Event>> eventsByWeek = new ArrayList<>(40);

    static {
        for (int i = 0; i < 40; i++) {
            eventsByWeek.add(new ArrayList<>());
        }
    }

    private static final Event W1_CHARLOTTE_ARRIVES = new Builder("Charlotte Arrives")
            .week(0)
            .effect(CHEERFUL, +1).build();
    private static final Event W2_JULIANNA_ARRIVES = new Builder("Julianna Arrives")
            .week(1)
            .choice(new Builder("Send Her Away")
                    .effect(ANGRY, +1)
                    .effect(YIELDING, +1)
                    .build(), o -> {
                new Builder("Approached by a Priestess")
                        .week(5)
                        .build();
                return true;
            })
            .choice(new Builder("Arrest Her")
                    .effect(ANGRY, +1)
                    .build(), o -> {
                //TODO julianna in dungeon
                return true;
            })
            .choice(new Builder("Let Her Stay")
                    .effect(WILLFUL, +1)
                    .build(), o -> true)
            .build();
    private static final Event W3_SNAKE_ATTACK = new Builder("Snake Attack")
            .week(2)
            .event(new Builder("Don't move!").build(),
                    game -> game.hasFlag("JULIANNA_AVAILABLE"))
            .event(new Builder("Charlotte, don't move!")
                    .event(new Builder("Success")
                                    .effect(ANGRY, +1)
                                    .build(),
                            game -> Skill.REFLEXES.getEffectiveLevel(game.getOutfit()) >= 20)
                    .event(new Builder("Failure")
                                    .effect(AFRAID, +1)
                                    .build(),
                            game -> Skill.REFLEXES.getEffectiveLevel(game.getOutfit()) < 20)
                    .build(), game -> !game.hasFlag("JULIANNA_AVAILABLE"))
            .build();

    private final String name;
    private final Map<Mood, Integer> moodEffectMap;
    private final Map<Event, Predicate<Game>> choices;
    private final Map<Event, Predicate<Game>> events;

    private Event(Builder builder) {
        this.name = builder.name;
        this.moodEffectMap = builder.moodEffectMap;
        this.choices = builder.choices;
        this.events = builder.events;
        if (builder.week != null) {
            eventsByWeek.get(builder.week).add(this);
        }
    }

    public void applyEffects(MoodValues moodValues) {
        moodValues.applyEffects(moodEffectMap);
    }

    public Map<Event, Predicate<Game>> getChoices() {
        return choices;
    }

    public Map<Event, Predicate<Game>> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }

    private static class Builder {
        private String name;
        private Integer week = null;
        private Map<Mood, Integer> moodEffectMap = new HashMap<>();
        private Map<Event, Predicate<Game>> choices = new LinkedHashMap<>();
        private Map<Event, Predicate<Game>> events = new LinkedHashMap<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder week(int week) {
            this.week = week;
            return this;
        }

        public Builder effect(Mood mood, int effect) {
            moodEffectMap.put(mood, effect);
            return this;
        }

        public Builder choice(Event event, Predicate<Game> predicate) {
            choices.put(event, predicate);
            return this;
        }

        public Builder event(Event event, Predicate<Game> predicate) {
            events.put(event, predicate);
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }

    public static List<Event> getEvents(int week) {
        return eventsByWeek.get(week);
    }
}
