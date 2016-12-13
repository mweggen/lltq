package nl.wggn.lltq;

import java.util.HashMap;
import java.util.Map;

import static nl.wggn.lltq.Mood.*;

/**
 * Created by Michiel on 12-12-2016.
 */
public enum Activity {
    ATTEND_COURT(new Activity
            .Builder("Attend Court")
            .addMoodEffect(YIELDING, +2)
            .addMoodEffect(DEPRESSED, +1)
            .addMoodEffect(PRESSURED, +1)),
    EXPLORE_CASTLE(new Activity
            .Builder("Explore Castle")
            .addMoodEffect(LONELY, +1)
            .addMoodEffect(AFRAID, +1)),
    VISIT_TOMB(new Activity
            .Builder("Visit Tomb")
            .addMoodEffect(DEPRESSED, +1)
            .addMoodEffect(AFRAID, +1)),
    ATTEND_SERVICE(new Activity
            .Builder("Attend Service")
            .addMoodEffect(AFRAID, -1)),
    WALK_IN_THE_GARDENS(new Activity
            .Builder("Walk In The Gardens")
            .addMoodEffect(LONELY, +1)
            .addMoodEffect(CHEERFUL, +1)),
    PLAY_WITH_TOYS(new Activity
            .Builder("Play With Toys")
            .addMoodEffect(YIELDING, +1)
            .addMoodEffect(LONELY, +1)
            .addMoodEffect(CHEERFUL, +1)),
    SNEAK_OUT(new Activity
            .Builder("Sneak Out")
            .addMoodEffect(WILLFUL, +2)
            .addMoodEffect(LONELY, +1)),
    VISIT_DUNGEONS(new Activity
            .Builder("Visit Dungeons")
            .addMoodEffect(YIELDING, +1)
            .addMoodEffect(AFRAID, +1)),

    TALK_TO_FATHER(new Activity
            .Builder("Talk To Father")),
    VISIT_JULIANNA(new Activity
            .Builder("Visit Julianna")),
    VISIT_TREASURY(new Activity
            .Builder("Visit Treasury"));

    private final String name;
    private final Map<Mood, Integer> moodMap;

    Activity(Builder builder) {
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
    }
}
