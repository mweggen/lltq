package nl.wggn.lltq;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

import static nl.wggn.lltq.SkillSubGroup.*;

/**
 * Created by Michiel on 12-12-2016.
 */
public enum SkillGroup {
    SOCIAL(ROYAL_DEMEANOR, CONVERSATION, EXPRESSION),
    PHYSICAL(AGILITY, WEAPONS, ATHLETICS, ANIMAL_HANDLING),
    INTELLECTUAL(HISTORY, INTRIGUE, MEDICINE, ECONOMICS, MILITARY),
    MYSTICAL(FAITH, LUMEN);

    static {
        init();
    }

    public static void init() {
        Stream.of(values()).forEach(sg -> sg.skillSubGroups.forEach(ssg -> ssg.setSkillGroup(sg)));
    }

    private final Set<SkillSubGroup> skillSubGroups;

    SkillGroup(SkillSubGroup first, SkillSubGroup... skillSubGroups) {
        this.skillSubGroups = EnumSet.of(first, skillSubGroups);
    }

    public double getBonus() {
        return skillSubGroups.stream().mapToDouble(SkillSubGroup::getLevel).sum() / 1000d;
    }
}
