package nl.wggn.lltq;

import java.util.HashMap;
import java.util.Map;

import static nl.wggn.lltq.SkillSubGroup.*;

/**
 * Created by Michiel on 12-12-2016.
 */
public enum Mood {
    AFRAID(new Mood.Builder("Afraid")
            .addBonus(+1, AGILITY, FAITH)
            .addBonus(-1, ROYAL_DEMEANOR, WEAPONS, INTRIGUE, MILITARY)),
    ANGRY(new Mood.Builder("Angry")
            .addBonus(+1, WEAPONS, MILITARY)
            .addBonus(-1, ROYAL_DEMEANOR, EXPRESSION, ANIMAL_HANDLING, MEDICINE)),
    DEPRESSED(new Mood.Builder("Depressed")
            .addBonus(+1, EXPRESSION, ANIMAL_HANDLING)
            .addBonus(-1, ROYAL_DEMEANOR, ATHLETICS)
            .addBonus(-2, CONVERSATION)),
    CHEERFUL(new Mood.Builder("Cheerful")
            .addBonus(+1, CONVERSATION, ATHLETICS)
            .addBonus(-1, WEAPONS, INTRIGUE)
            .addBonus(-2, MILITARY)),
    YIELDING(new Mood.Builder("Yielding")
            .addBonus(+1, ROYAL_DEMEANOR, HISTORY, FAITH)
            .addBonus(-3, WEAPONS, LUMEN)),
    WILLFUL(new Mood.Builder("Willful")
            .addBonus(+1, INTRIGUE, MILITARY, LUMEN)
            .addBonus(-2, ROYAL_DEMEANOR, HISTORY, ECONOMICS)),
    LONELY(new Mood.Builder("Lonely")
            .addBonus(+1, CONVERSATION, MEDICINE)
            .addBonus(-1, ROYAL_DEMEANOR, INTRIGUE, FAITH)),
    PRESSURED(new Mood.Builder("Pressured")
            .addBonus(+1, ATHLETICS, FAITH)
            .addBonus(-1, CONVERSATION, HISTORY, ECONOMICS)),
    NEUTRAL(new Mood.Builder("Neutral")),
    INJURED(new Mood.Builder("Injured")
            .addBonus(-3, AGILITY, WEAPONS, ATHLETICS, ANIMAL_HANDLING));

    private final String name;
    private final Map<SkillSubGroup, Integer> bonusMap;

    Mood(Builder builder) {
        name = builder.name;
        bonusMap = builder.bonusMap;
    }

    public int getBonus(SkillSubGroup skillSubGroup) {
        Integer i = bonusMap.get(skillSubGroup);
        return i == null ? 0 : i;
    }

    @Override
    public String toString() {
        return name;
    }

    static class Builder {
        private String name;
        private Map<SkillSubGroup, Integer> bonusMap = new HashMap<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder addBonus(int amount, SkillSubGroup... skillSubGroup) {
            for (SkillSubGroup subGroup : skillSubGroup) {
                bonusMap.put(subGroup, amount);
            }
            return this;
        }
    }
}
