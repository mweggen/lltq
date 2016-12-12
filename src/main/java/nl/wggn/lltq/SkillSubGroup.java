package nl.wggn.lltq;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static nl.wggn.lltq.SkillGroup.*;

/**
 * Created by Michiel on 12-12-2016.
 */
public class SkillSubGroup {

    private static final Map<SkillGroup, Set<SkillSubGroup>> skillSubGroupMap = new HashMap<>();

    public static final SkillSubGroup ROYAL_DEMEANOR = new SkillSubGroup(SOCIAL);
    public static final SkillSubGroup CONVERSATION = new SkillSubGroup(SOCIAL);
    public static final SkillSubGroup EXPRESSION = new SkillSubGroup(SOCIAL);

    public static final SkillSubGroup AGILITY = new SkillSubGroup(PHYSICAL);
    public static final SkillSubGroup WEAPONS = new SkillSubGroup(PHYSICAL);
    public static final SkillSubGroup ATHLETICS = new SkillSubGroup(PHYSICAL);
    public static final SkillSubGroup ANIMAL_HANDLING = new SkillSubGroup(PHYSICAL);

    public static final SkillSubGroup HISTORY = new SkillSubGroup(INTELLECTUAL);
    public static final SkillSubGroup INTRIGUE = new SkillSubGroup(INTELLECTUAL);
    public static final SkillSubGroup MEDICINE = new SkillSubGroup(INTELLECTUAL);
    public static final SkillSubGroup ECONOMICS = new SkillSubGroup(INTELLECTUAL);
    public static final SkillSubGroup MILITARY = new SkillSubGroup(INTELLECTUAL);

    public static final SkillSubGroup FAITH = new SkillSubGroup(MYSTICAL);
    public static final SkillSubGroup LUMEN = new SkillSubGroup(MYSTICAL);

    private final SkillGroup skillGroup;

    private SkillSubGroup(SkillGroup skillGroup) {
        this.skillGroup = skillGroup;
        skillSubGroupMap.computeIfAbsent(skillGroup, k -> new HashSet<>()).add(this);
    }

    public SkillGroup getSkillGroup() {
        return skillGroup;
    }

    public double getLevel() {
        return Skill.getSkills(this)
                .stream()
                .mapToDouble(Skill::getLevel)
                .sum();
    }

    public double getBonus(Mood mood) {
        return mood.getBonus(this)
                + getLevel() / 100d
                + skillGroup.getBonus();//TODO outfit
    }

    public double getMinLevel() {
        return Skill.getSkills(this)
                .stream()
                .mapToDouble(Skill::getLevel)
                .min()
                .getAsDouble();
    }

    public static Set<SkillSubGroup> getSkillSubGroups(SkillGroup skillGroup) {
        return skillSubGroupMap.get(skillGroup);
    }
}
