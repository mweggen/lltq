package nl.wggn.lltq;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

import static nl.wggn.lltq.Skill.*;

/**
 * Created by Michiel on 12-12-2016.
 */
public enum SkillSubGroup {

    ROYAL_DEMEANOR(COMPOSURE, ELEGANCE, PRESENCE),
    CONVERSATION(PUBLIC_SPEAKING, COURT_MANNERS, FLATTERY),
    EXPRESSION(DECORATION, INSTRUMENT, VOICE),

    AGILITY(DANCE, REFLEXES, FLEXIBILITY),
    WEAPONS(SWORDS, ARCHERY, POLEARMS),
    ATHLETICS(RUNNING, CLIMBING, SWIMMING),
    ANIMAL_HANDLING(HORSES, DOGS, FALCONS),

    HISTORY(NOVAN, FOREIGN_AFFAIRS, WORLD),
    INTRIGUE(INTERNAL_AFFAIRS, FOREIGN_INTELLIGENCE, CIPHERING),
    MEDICINE(HERBS, BATTLEFIELD, POISON),
    ECONOMICS(ACCOUNTING, TRADE, PRODUCTION),
    MILITARY(STRATEGY, NAVAL_STRATEGY, LOGISTICS),

    FAITH(MEDITATION, DIVINATION, LORE),
    LUMEN(SENSE_MAGIC, RESIST_MAGIC, WIELD_MAGIC);

    static {
        init();
    }

    public static void init() {
        Stream.of(values()).forEach(ssg -> ssg.skills.forEach(s -> s.setSkillSubGroup(ssg)));
    }

    private SkillGroup skillGroup;
    private final Set<Skill> skills;

    SkillSubGroup(Skill first, Skill... skills) {
        this.skillGroup = skillGroup;
        this.skills = EnumSet.of(first, skills);
    }

    public void setSkillGroup(SkillGroup skillGroup) {
        this.skillGroup = skillGroup;
    }

    public double getLevel() {
        return skills.stream()
                .mapToDouble(Skill::getLevel)
                .sum();
    }

    public double getEffectiveLevel(Outfit outfit) {
        return skills.stream()
                .mapToDouble(s -> s.getEffectiveLevel(outfit))
                .sum();
    }

    public double getBonus(Mood mood) {
        return mood.getBonus(this)
                + getLevel() / 100d
                + skillGroup.getBonus();
    }

    public double getMinLevel() {
        return skills.stream()
                .mapToDouble(Skill::getLevel)
                .min()
                .getAsDouble();
    }
}
