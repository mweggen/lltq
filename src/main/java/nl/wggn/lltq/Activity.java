package nl.wggn.lltq;

import java.util.HashMap;
import java.util.Map;

import static nl.wggn.lltq.Mood.*;

/**
 * Created by Michiel on 12-12-2016.
 */
public class Activity {
    public static final Activity ATTEND_COURT = new Activity
            .Builder("Attend Court")
            .addMoodEffect(YIELDING, +2)
            .addMoodEffect(DEPRESSED, +1)
            .addMoodEffect(PRESSURED, +1)
            .build();
    public static final Activity EXPLORE_CASTLE = new Activity
            .Builder("Explore Castle")
            .addMoodEffect(LONELY, +1)
            .addMoodEffect(AFRAID, +1)
            .build();
    public static final Activity PLAY_WITH_TOYS = new Activity
            .Builder("Play With Toys")
            .addMoodEffect(YIELDING, +1)
            .addMoodEffect(LONELY, +1)
            .addMoodEffect(CHEERFUL, +1)
            .build();
    public static final Activity ATTEND_SERVICE = new Activity
            .Builder("Attend Service")
            .addMoodEffect(AFRAID, -1)
            .build();
    public static final Activity SNEAK_OUT = new Activity
            .Builder("Sneak Out")
            .addMoodEffect(WILLFUL, +2)
            .addMoodEffect(LONELY, +1)
            .build();
    public static final Activity VISIT_TREASURY = new Activity
            .Builder("Visit Treasury")
            .build();
    public static final Activity VISIT_TOMB = new Activity
            .Builder("Visit Tomb")
            .addMoodEffect(AFRAID, +1)
            .addMoodEffect(DEPRESSED, +1)
            .build();
    public static final Activity WALK_IN_THE_GARDENS = new Activity
            .Builder("Walk In The Gardens")
            .addMoodEffect(CHEERFUL, +1)
            .addMoodEffect(LONELY, +1)
            .build();
    public static final Activity VISIT_DUNGEONS = new Activity
            .Builder("Visit Dungeons")
            .build();

    public static final Activity VISIT_JULIANNA = new Activity
            .Builder("Visit Julianna")
            .build();
    public static final Activity TALK_TO_FATHER = new Activity
            .Builder("Talk To Father")
            .build();

    private final String name;
    private final Map<Mood, Integer> moodMap;

    private Activity(Builder builder) {
        this.name = builder.name;
        this.moodMap = builder.moodMap;
    }

    public void applyEffects(MoodValues moodValues) {
        moodValues.applyEffects(moodMap);
    }

    @Override
    public String toString() {
        return name;
    }

    private static class Builder {
        private String name;
        private Map<Mood, Integer> moodMap = new HashMap<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder addMoodEffect(Mood mood, int effect) {
            moodMap.put(mood, effect);
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }
}
