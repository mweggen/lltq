package nl.wggn.lltq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.wggn.lltq.Mood.*;

/**
 * Created by Michiel on 12-12-2016.
 */
public class MoodValues {

    private static final int CAP = 4;

    private static final Map<Mood, MoodValue> lowerMoods = new HashMap<>();
    private static final Map<Mood, MoodValue> upperMoods = new HashMap<>();

    private final MoodValue afraidAngry;
    private final MoodValue depressedCheerful;
    private final MoodValue yieldingWillful;
    private final MoodValue lonelyPressured;

    static class MoodValue {
        private Mood lower;
        private Mood upper;
        private int value;

        public MoodValue(Mood lower, Mood upper) {
            this.lower = lower;
            this.upper = upper;
            lowerMoods.put(lower, this);
            upperMoods.put(upper, this);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Mood getLower() {
            return lower;
        }

        public Mood getUpper() {
            return upper;
        }

        public void increase(int amount) {
            value = Math.max(-CAP, Math.min(CAP, value + amount));
        }

        public void decrease(int amount) {
            if (amount > 0) {
                value = Math.min(Math.max(0, value), value + amount);
            } else {
                value = Math.max(Math.min(0, value), value + amount);
            }
        }
    }

    private List<MoodValue> moodValues = new ArrayList<>();

    public MoodValues(int afraidAngry, int depressedCheerful, int yieldingWillful, int lonelyPressured) {
        this.afraidAngry = new MoodValue(AFRAID, ANGRY);
        this.depressedCheerful = new MoodValue(DEPRESSED, CHEERFUL);
        this.yieldingWillful = new MoodValue(YIELDING, WILLFUL);
        this.lonelyPressured = new MoodValue(LONELY, PRESSURED);

        moodValues.add(this.afraidAngry);
        moodValues.add(this.depressedCheerful);
        moodValues.add(this.yieldingWillful);
        moodValues.add(this.lonelyPressured);

        this.afraidAngry.setValue(afraidAngry);
        this.depressedCheerful.setValue(depressedCheerful);
        this.yieldingWillful.setValue(yieldingWillful);
        this.lonelyPressured.setValue(lonelyPressured);
    }

    public Mood getMood() {

        //TODO injured

        if (!moodValues.stream().mapToInt(MoodValue::getValue).allMatch(i -> i == 0)) {

            for (int i = 0; i < moodValues.size() - 1; i++) {
                MoodValue moodValue = moodValues.get(i);
                int abs = Math.abs(moodValue.getValue());
                if (moodValues.subList(i + 1, moodValues.size()).stream()
                        .mapToInt(MoodValue::getValue)
                        .map(Math::abs)
                        .allMatch(j -> abs >= j)) {
                    if (moodValue.getValue() > 0) {
                        return moodValue.getUpper();
                    } else {
                        return moodValue.getLower();
                    }
                }
            }
            MoodValue moodValue = moodValues.get(moodValues.size() - 1);
            if (moodValue.getValue() > 0) {
                return moodValue.getUpper();
            } else {
                return moodValue.getLower();
            }
        }

        return NEUTRAL;
    }

    public void applyEffects(Map<Mood, Integer> moodMap) {
        String output = "";
        for (Map.Entry<Mood, Integer> entry : moodMap.entrySet()) {
            Integer effect = entry.getValue();
            String result;
            if (effect > 0) {
                result = increase(entry.getKey(), effect);
            } else {
                result = decrease(entry.getKey(), -effect);
            }
            if (result.length() > 0) {
                if (output.length() > 0) {
                    output += ", ";
                }
                output += result;
            }
        }
        if (output.length() > 0) {
            System.out.print(output + ".");
        } else {
//            System.out.print("No change.");
        }
    }

    public String increase(Mood mood, int amount) {
        MoodValue moodValue = upperMoods.get(mood);
        int old;
        if (moodValue == null) {
            moodValue = lowerMoods.get(mood);
            old = moodValue.getValue();

            moodValue.increase(-amount);
        } else {
            old = moodValue.getValue();
            moodValue.increase(amount);
        }
        int abs = Math.abs(old - moodValue.getValue());
        if (abs > 0) {
            return "+" + abs + " " + mood;
        } else {
            return "";
        }
    }

    public String decrease(Mood mood, int amount) {
        MoodValue moodValue = upperMoods.get(mood);
        int diff;
        if (moodValue == null) {
            moodValue = lowerMoods.get(mood);
            diff = moodValue.getValue();

            moodValue.decrease(amount);

            diff = diff - moodValue.getValue();
        } else {
            diff = moodValue.getValue();
            moodValue.decrease(-amount);

            diff -= moodValue.getValue();
        }

        if (diff > 0) {
            return "-" + diff + " " + mood;
        } else {
            return "";
        }
    }

    public int getAfraidAngry() {
        return afraidAngry.getValue();
    }

    public int getDepressedCheerful() {
        return depressedCheerful.getValue();
    }

    public int getYieldingWillful() {
        return yieldingWillful.getValue();
    }

    public int getLonelyPressured() {
        return lonelyPressured.getValue();
    }
}
