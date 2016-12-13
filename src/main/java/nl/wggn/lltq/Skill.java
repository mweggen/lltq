package nl.wggn.lltq;

/**
 * Created by Michiel on 12-12-2016.
 */
public enum Skill {

    COMPOSURE("Composure"),
    ELEGANCE("Elegance"),
    PRESENCE("Presence"),

    PUBLIC_SPEAKING("Public Speaking"),
    COURT_MANNERS("Court Manners"),
    FLATTERY("Flattery"),

    DECORATION("Decoration"),
    INSTRUMENT("Instrument"),
    VOICE("Voice"),

    DANCE("Dance"),
    REFLEXES("Reflexes"),
    FLEXIBILITY("Flexibility"),

    SWORDS("Swords"),
    ARCHERY("Archery"),
    POLEARMS("Polearms"),

    RUNNING("Running"),
    CLIMBING("Climbing"),
    SWIMMING("Swimming"),

    HORSES("Horses"),
    DOGS("Dogs"),
    FALCONS("Falcons"),

    NOVAN("Novan"),
    FOREIGN_AFFAIRS("Foreign Affairs"),
    WORLD("World"),

    INTERNAL_AFFAIRS("Internal Affairs"),
    FOREIGN_INTELLIGENCE("Foreign Intelligence"),
    CIPHERING("Ciphering"),

    HERBS("Herbs"),
    BATTLEFIELD("Battlefield"),
    POISON("Poison"),

    ACCOUNTING("Accounting"),
    TRADE("Trade"),
    PRODUCTION("Production"),

    STRATEGY("Strategy"),
    NAVAL_STRATEGY("Naval Strategy"),
    LOGISTICS("Logistics"),

    MEDITATION("Meditation"),
    DIVINATION("Divination"),
    LORE("Lore"),

    SENSE_MAGIC("Sense Magic"),
    RESIST_MAGIC("Resist Magic"),
    WIELD_MAGIC("Wield Magic");

    private double level = 0;
    private SkillSubGroup skillSubGroup;
    private final String name;

    Skill(String name) {
        this.name = name;
    }

    public void setSkillSubGroup(SkillSubGroup skillSubGroup) {
        this.skillSubGroup = skillSubGroup;
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
}
