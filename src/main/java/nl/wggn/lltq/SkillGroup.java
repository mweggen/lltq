package nl.wggn.lltq;

/**
 * Created by Michiel on 12-12-2016.
 */
public class SkillGroup {
    public static final SkillGroup SOCIAL = new SkillGroup();
    public static final SkillGroup PHYSICAL = new SkillGroup();
    public static final SkillGroup INTELLECTUAL = new SkillGroup();
    public static final SkillGroup MYSTICAL = new SkillGroup();

    public double getBonus() {
        return SkillSubGroup.getSkillSubGroups(this).stream().mapToDouble(SkillSubGroup::getLevel).sum() / 1000d;
    }
}
