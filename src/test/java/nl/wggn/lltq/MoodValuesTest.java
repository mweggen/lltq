package nl.wggn.lltq;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Michiel on 12-12-2016.
 */
public class MoodValuesTest {

    private MoodValues moodValues;

    @Test
    public void afraid() throws Exception {
        moodValues = new MoodValues(0, 0, 0, 0);
        moodValues.increase(Mood.AFRAID, 1);
        assertThat(moodValues.getMood(), is(Mood.AFRAID));
        moodValues.decrease(Mood.AFRAID, 2);
        assertThat(moodValues.getMood(), is(Mood.NEUTRAL));

        moodValues.increase(Mood.AFRAID, -1);
        assertThat(moodValues.getMood(), is(Mood.ANGRY));
        moodValues.decrease(Mood.AFRAID, 1);
        assertThat(moodValues.getMood(), is(Mood.ANGRY));

        moodValues.increase(Mood.AFRAID, 10);
        assertThat(moodValues.getMood(), is(Mood.AFRAID));
        moodValues.decrease(Mood.AFRAID, 4);
        assertThat(moodValues.getMood(), is(Mood.NEUTRAL));
    }

    @Test
    public void lonely() throws Exception {
        moodValues = new MoodValues(0, 0, 0, 0);
        moodValues.increase(Mood.LONELY, 1);
        assertThat(moodValues.getMood(), is(Mood.LONELY));
        moodValues.decrease(Mood.LONELY, 2);
        assertThat(moodValues.getMood(), is(Mood.NEUTRAL));

        moodValues.increase(Mood.LONELY, -1);
        assertThat(moodValues.getMood(), is(Mood.PRESSURED));
        moodValues.decrease(Mood.LONELY, 1);
        assertThat(moodValues.getMood(), is(Mood.PRESSURED));

        moodValues.increase(Mood.LONELY, 10);
        assertThat(moodValues.getMood(), is(Mood.LONELY));
        moodValues.decrease(Mood.LONELY, 4);
        assertThat(moodValues.getMood(), is(Mood.NEUTRAL));
    }
}
