package nl.wggn.lltq;

import static nl.wggn.lltq.SkillSubGroup.*;

/**
 * Created by Michiel on 13-12-2016.
 */
public class Outfit {
    public static final Outfit BOARDING_UNIFORM = new Outfit("Boarding Uniform");
    public static final Outfit CORONET = new Outfit("Coronet", ROYAL_DEMEANOR);

    private final String name;
    private SkillSubGroup skillSubGroup;

    public Outfit(String name) {
        this.name = name;
    }

    public Outfit(String name, SkillSubGroup skillSubGroup) {
        this.name = name;
        this.skillSubGroup = skillSubGroup;
    }

    public SkillSubGroup getSkillSubGroup() {
        return skillSubGroup;
    }
}
