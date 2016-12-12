package nl.wggn.lltq;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static nl.wggn.lltq.SkillSubGroup.*;
import static nl.wggn.lltq.SkillSubGroup.AGILITY;

/**
 * Created by Michiel on 12-12-2016.
 */
public class Skill {

    private static final Map<SkillSubGroup, Set<Skill>> skillMap = new HashMap<>();

    public static final Skill COMPOSURE = new Skill(ROYAL_DEMEANOR, "COMPOSURE");
    public static final Skill ELEGANCE = new Skill(ROYAL_DEMEANOR, "ELEGANCE");
    public static final Skill PRESENCE = new Skill(ROYAL_DEMEANOR, "PRESENCE");

    public static final Skill PUBLIC_SPEAKING = new Skill(CONVERSATION, "PUBLIC_SPEAKING");
    public static final Skill COURT_MANNERS = new Skill(CONVERSATION, "COURT_MANNERS");
    public static final Skill FLATTERY = new Skill(CONVERSATION, "FLATTERY");

    public static final Skill DECORATION = new Skill(EXPRESSION, "DECORATION");
    public static final Skill INSTRUMENT = new Skill(EXPRESSION, "INSTRUMENT");
    public static final Skill VOICE = new Skill(EXPRESSION, "VOICE");

    public static final Skill DANCE = new Skill(AGILITY, "DANCE");
    public static final Skill REFLEXES = new Skill(AGILITY, "REFLEXES");
    public static final Skill FLEXIBILITY = new Skill(AGILITY, "FLEXIBILITY");

    //TODO
    public static final Skill SWORDS = new Skill(WEAPONS, "SWORDS");
    public static final Skill RUNNING = new Skill(ATHLETICS, "RUNNING");
    public static final Skill HORSES = new Skill(ANIMAL_HANDLING, "HORSES");
    public static final Skill NOVAN = new Skill(HISTORY, "NOVAN");
    public static final Skill INTERNAL_AFFAIRS = new Skill(INTRIGUE, "Internal Affairs");
    public static final Skill HERBS = new Skill(MEDICINE, "Herbs");

    public static final Skill ACCOUNTING = new Skill(ECONOMICS, "Accounting");
    public static final Skill TRADE = new Skill(ECONOMICS, "Trade");

    public static final Skill STRATEGY = new Skill(MILITARY, "Strategy");
    public static final Skill MEDITATION = new Skill(FAITH, "Meditation");
    public static final Skill SENSE_MAGIC = new Skill(LUMEN, "Sense Magic");

    private double level = 0;
    private final SkillSubGroup skillSubGroup;
    private final String name;

    private Skill(SkillSubGroup skillSubGroup, String name) {
        this.skillSubGroup = skillSubGroup;
        this.name = name;
        skillMap.computeIfAbsent(skillSubGroup, k -> new HashSet<>()).add(this);
    }

    public double getLevel() {
        return level;
    }

    public double getEffectiveLevel(Outfit outfit) {
        return level + (outfit.getSkillSubGroup() == skillSubGroup ? 10 : 0);
    }

    public void increaseLevel(double amount) {
        int cap = skillSubGroup.getMinLevel() < 25 ? 50 : 100;
        level = Math.min(level + amount, cap);
    }

    public double getBonus(Mood mood) {
        return skillSubGroup.getBonus(mood);
    }

    public SkillSubGroup getSkillSubGroup() {
        return skillSubGroup;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Set<Skill> getSkills(SkillSubGroup skillSubGroup) {
        return skillMap.get(skillSubGroup);
    }
}
