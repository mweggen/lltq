package nl.wggn.lltq;

import java.util.Map;

import static nl.wggn.lltq.Mood.AFRAID;
import static nl.wggn.lltq.Mood.CHEERFUL;
import static nl.wggn.lltq.Mood.YIELDING;

/**
 * Created by Michiel on 12-12-2016.
 */
public class MoodValues {

    private  static final int CAP = 4;

    private int afraidAngry;
    private int depressedCheerful;
    private int yieldingWillful;
    private int lonelyPressured;

    public MoodValues(int afraidAngry, int depressedCheerful, int yieldingWillful, int lonelyPressured) {
        this.afraidAngry = afraidAngry;
        this.depressedCheerful = depressedCheerful;
        this.yieldingWillful = yieldingWillful;
        this.lonelyPressured = lonelyPressured;
    }

    public Mood getMood() {

        //TODO injured

        Mood mood = Mood.NEUTRAL;

        if (!(afraidAngry == 0 && depressedCheerful == 0 && yieldingWillful == 0 && lonelyPressured == 0)) {

            int absAfraidAngry = Math.abs(afraidAngry);
            int absDepressedCheerful = Math.abs(depressedCheerful);
            int absYieldingWillful = Math.abs(yieldingWillful);
            int absLonelyPressured = Math.abs(lonelyPressured);

            if (absAfraidAngry >= absDepressedCheerful
                    && absAfraidAngry >= absYieldingWillful
                    && absAfraidAngry >= absLonelyPressured) {
                if (afraidAngry > 0) {
                    mood = Mood.ANGRY;
                } else {
                    mood = Mood.AFRAID;
                }
            } else if (absDepressedCheerful >= absYieldingWillful
                    && absDepressedCheerful >= absLonelyPressured) {
                if (depressedCheerful > 0) {
                    mood = Mood.CHEERFUL;
                } else {
                    mood = Mood.DEPRESSED;
                }
            } else if (absYieldingWillful >= absLonelyPressured) {
                if (yieldingWillful > 0) {
                    mood = Mood.WILLFUL;
                } else {
                    mood = Mood.YIELDING;
                }
            } else {
                if (lonelyPressured > 0) {
                    mood = Mood.PRESSURED;
                } else {
                    mood = Mood.LONELY;
                }
            }
        }

        return mood;
    }

    public void applyEffects(Map<Mood, Integer> moodMap) {
        boolean first = true;
        for (Map.Entry<Mood, Integer> entry : moodMap.entrySet()) {
            if (first) {
                first = false;
            } else {
                System.out.print(", ");
            }
            if (entry.getKey() == AFRAID) {
                Integer effect = entry.getValue();
                if (effect > 0) {
                    incAfraid(effect);
                } else {
                    decAfraid(effect);
                }
            }
            if (entry.getKey() == CHEERFUL) {
                Integer effect = entry.getValue();
                if (effect > 0) {
                    incCheerful(effect);
                } else {
                    decCheerful(effect);
                }
            }
            if (entry.getKey() == YIELDING) {
                Integer effect = entry.getValue();
                if (effect > 0) {
                    incYielding(effect);
                } else {
                    decYielding(effect);
                }
            }
        }
        if (!first) {
            System.out.println(".");
        }
    }

    public void incAfraid(int i) {
        int oldAfraidAngry = afraidAngry;
        afraidAngry = Math.max(-CAP, afraidAngry - i);
        if (oldAfraidAngry > afraidAngry) {
            System.out.print("+" + (oldAfraidAngry - afraidAngry) + " Afraid");
        }
    }

    public void decAfraid(int i) {
        afraidAngry = Math.min(Math.max(0, afraidAngry), afraidAngry + i);
    }

    public void incAngry(int i) {
        afraidAngry = Math.min(CAP, afraidAngry + i);
    }

    public void decAngry(int i) {
        afraidAngry = Math.max(Math.min(0, afraidAngry), afraidAngry - i);
    }

    public void incDepressed(int i) {
        depressedCheerful = Math.max(-CAP, depressedCheerful - i);
    }

    public void decDepressed(int i) {
        depressedCheerful = Math.min(Math.max(0, depressedCheerful), depressedCheerful + i);
    }

    public void incCheerful(int i) {
        int oldDepressedCheerful = depressedCheerful;
        depressedCheerful = Math.min(CAP, depressedCheerful + i);
        if (oldDepressedCheerful < depressedCheerful) {
            System.out.print("+" + (depressedCheerful - oldDepressedCheerful) + " Cheerful");
        }
    }

    public void decCheerful(int i) {
        depressedCheerful = Math.max(Math.min(0, depressedCheerful), depressedCheerful - i);
    }

    public void incYielding(int i) {
        int oldYieldingWillful = yieldingWillful;
        yieldingWillful = Math.max(-CAP, yieldingWillful - i);
        if (oldYieldingWillful > yieldingWillful) {
            System.out.print("+" + (oldYieldingWillful - yieldingWillful) + " Yielding");
        }
    }

    public void decYielding(int i) {
        yieldingWillful = Math.min(Math.max(0, yieldingWillful), yieldingWillful + i);
    }

    public void incWillful(int i) {
        yieldingWillful = Math.min(CAP, yieldingWillful + i);
    }

    public void decWillful(int i) {
        yieldingWillful = Math.max(Math.min(0, yieldingWillful), yieldingWillful - i);
    }

    public void incLonely(int i) {
        lonelyPressured = Math.max(-CAP, lonelyPressured - i);
    }

    public void decLonely(int i) {
        lonelyPressured = Math.min(Math.max(0, lonelyPressured), lonelyPressured + i);
    }

    public void incPressured(int i) {
        lonelyPressured = Math.min(CAP, lonelyPressured + i);
    }

    public void decPressured(int i) {
        lonelyPressured = Math.max(Math.min(0, lonelyPressured), lonelyPressured - i);
    }

    public int getAfraidAngry() {
        return afraidAngry;
    }

    public int getDepressedCheerful() {
        return depressedCheerful;
    }

    public int getYieldingWillful() {
        return yieldingWillful;
    }

    public int getLonelyPressured() {
        return lonelyPressured;
    }
}
