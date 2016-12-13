package nl.wggn.lltq;

import javax.el.ELProcessor;
import java.util.Locale;
import java.util.stream.Stream;

import static nl.wggn.lltq.Activity.ATTEND_COURT;
import static nl.wggn.lltq.Activity.ATTEND_SERVICE;
import static nl.wggn.lltq.Activity.PLAY_WITH_TOYS;
import static nl.wggn.lltq.Activity.VISIT_DUNGEONS;
import static nl.wggn.lltq.Activity.VISIT_JULIANNA;
import static nl.wggn.lltq.Skill.BATTLEFIELD;
import static nl.wggn.lltq.Skill.COMPOSURE;
import static nl.wggn.lltq.Skill.ELEGANCE;
import static nl.wggn.lltq.Skill.PRESENCE;
import static nl.wggn.lltq.Skill.PUBLIC_SPEAKING;
import static nl.wggn.lltq.Skill.TRADE;

/**
 * Created by Michiel on 12-12-2016.
 */
public class Main {

    private static Skill[] classes = {
            TRADE, TRADE,//1
            PUBLIC_SPEAKING, BATTLEFIELD,//2
            ELEGANCE, ELEGANCE,//3
            PRESENCE, PRESENCE,//4
            COMPOSURE, COMPOSURE,//5
            PRESENCE, PRESENCE,//6
            ELEGANCE, COMPOSURE,//7
            ELEGANCE, COMPOSURE,//8
            ELEGANCE, COMPOSURE,//9
            ELEGANCE, COMPOSURE,//10
            ELEGANCE, COMPOSURE,//11
            ELEGANCE, COMPOSURE,//12
            ELEGANCE, COMPOSURE,//13
            ELEGANCE, COMPOSURE,//14
            ELEGANCE, COMPOSURE,//15
            ELEGANCE, COMPOSURE,//16
            ELEGANCE, COMPOSURE,//17
            ELEGANCE, COMPOSURE,//18
            ELEGANCE, COMPOSURE,//19
            ELEGANCE, COMPOSURE,//20
            ELEGANCE, COMPOSURE,//21
            ELEGANCE, COMPOSURE,//22
            ELEGANCE, COMPOSURE,//23
            ELEGANCE, COMPOSURE,//24
            ELEGANCE, COMPOSURE,//25
            ELEGANCE, COMPOSURE,//26
            ELEGANCE, COMPOSURE,//27
            ELEGANCE, COMPOSURE,//28
            ELEGANCE, COMPOSURE,//29
            ELEGANCE, COMPOSURE,//30
            ELEGANCE, COMPOSURE,//31
            ELEGANCE, COMPOSURE,//32
            ELEGANCE, COMPOSURE,//33
            ELEGANCE, COMPOSURE,//34
            ELEGANCE, COMPOSURE,//35
            ELEGANCE, COMPOSURE,//36
            ELEGANCE, COMPOSURE,//37
            ELEGANCE, COMPOSURE,//38
            ELEGANCE, COMPOSURE,//39
            ELEGANCE, COMPOSURE //40
    };
    private static Activity[] activities = {
            PLAY_WITH_TOYS,//1
            PLAY_WITH_TOYS,//2
            PLAY_WITH_TOYS,//3
            VISIT_DUNGEONS,//4
            ATTEND_SERVICE,//5
            VISIT_JULIANNA,//6
            ATTEND_COURT,//7
            ATTEND_COURT,//8
            ATTEND_COURT,//9
            ATTEND_COURT,//10
            ATTEND_COURT,//11
            ATTEND_COURT,//12
            ATTEND_COURT,//13
            ATTEND_COURT,//14
            ATTEND_COURT,//15
            ATTEND_COURT,//16
            ATTEND_COURT,//17
            ATTEND_COURT,//18
            ATTEND_COURT,//19
            ATTEND_COURT,//20
            ATTEND_COURT,//21
            ATTEND_COURT,//22
            ATTEND_COURT,//23
            ATTEND_COURT,//24
            ATTEND_COURT,//25
            ATTEND_COURT,//26
            ATTEND_COURT,//27
            ATTEND_COURT,//28
            ATTEND_COURT,//29
            ATTEND_COURT,//30
            ATTEND_COURT,//31
            ATTEND_COURT,//32
            ATTEND_COURT,//33
            ATTEND_COURT,//34
            ATTEND_COURT,//35
            ATTEND_COURT,//36
            ATTEND_COURT,//37
            ATTEND_COURT,//38
            ATTEND_COURT,//39
            ATTEND_COURT //40
    };

    public static void main(String[] args) {

        SkillGroup.init();
        SkillSubGroup.init();

        Locale.setDefault(Locale.ENGLISH);
        ELProcessor elp = new ELProcessor();

        MoodValues moodValues = new MoodValues(-2, -4, 0, 0);
        Outfit outfit = Outfit.BOARDING_UNIFORM;

        for (int i = 0; i < 6; i++) {

            Stream.of(Skill.values()).forEach(s -> elp.defineBean(s.toString().replaceAll("\\s",""), s.getEffectiveLevel(outfit)));

            System.out.println("trade: "+elp.eval("Trade >= 20"));
            System.out.println("cm: "+elp.eval("CourtManners"));

            Mood mood = moodValues.getMood();
            System.out.println("Mood: " + mood + " ("+prependPlus(moodValues.getAfraidAngry())+","+prependPlus(moodValues.getDepressedCheerful())+","+prependPlus(moodValues.getYieldingWillful())+","+prependPlus(moodValues.getLonelyPressured())+")");

            Skill class1 = classes[i*2];
            Skill class2 = classes[i*2+1];

            double bonus1 = class1.getBonus(mood);
            double bonus2 = class2.getBonus(mood);

            attendClass(class1, bonus1, mood);
            attendClass(class2, bonus2, mood);

            for (Event event : Event.getEvents(i)) {
                System.out.print("Event: " + event + ". ");
                event.applyEffects(moodValues);
                System.out.println();
            }

            System.out.print("Activity: " + activities[i] + ". ");
            activities[i].applyEffects(moodValues);
            System.out.println();

            System.out.println();
        }
    }

    private static void attendClass(Skill skill, double bonus, Mood mood) {
        double oldLevel2 = skill.getLevel();
        skill.increaseLevel(Math.max(0, bonus + 2) * 5);

        if (skill.getLevel() - oldLevel2 > 0) {
            System.out.printf("Study: " + skill.toString() + " (%.2f). ", skill.getLevel());
            if (mood.getBonus(skill.getSkillSubGroup()) < 0) {
                System.out.print("Penalty: " + mood);
            } else if (mood.getBonus(skill.getSkillSubGroup()) > 0) {
                System.out.print("Bonus: " + mood);
            }
            System.out.println();
        } else {
/*
            System.out.println(skill.toString() + " did not increase");
*/
        }

    }

    public static String prependPlus(int i) {
        String s = "";
        if (i > 0) {
            s += "+";
        }
        return s + i;
    }
}
