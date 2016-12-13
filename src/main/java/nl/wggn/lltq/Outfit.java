package nl.wggn.lltq;

import static nl.wggn.lltq.SkillSubGroup.*;

/**
 * Created by Michiel on 13-12-2016.
 */
public enum Outfit {
    BOARDING_UNIFORM("Boarding Uniform"),
    CORONET("Coronet", ROYAL_DEMEANOR),
    TEA_DRESS("Tea Dress", CONVERSATION),
    MAGICAL_GIRL("Magical Girl", LUMEN),
    PRIESTESS_ROBE("Priestess Robe", FAITH),
    TOGA("Toga", EXPRESSION);

    private final String name;
    private SkillSubGroup skillSubGroup;

    Outfit(String name) {
        this.name = name;
    }

    Outfit(String name, SkillSubGroup skillSubGroup) {
        this.name = name;
        this.skillSubGroup = skillSubGroup;
    }

    public SkillSubGroup getSkillSubGroup() {
        return skillSubGroup;
    }
}
