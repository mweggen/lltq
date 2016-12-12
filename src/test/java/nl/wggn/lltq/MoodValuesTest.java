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
        moodValues = new MoodValues();
        moodValues.incAfraid(1);
        assertThat(moodValues.getMood(), is(Mood.AFRAID));
        moodValues.decAfraid(2);
        assertThat(moodValues.getMood(), is(Mood.NEUTRAL));

        moodValues.incAfraid(-1);
        assertThat(moodValues.getMood(), is(Mood.ANGRY));
        moodValues.decAfraid(1);
        assertThat(moodValues.getMood(), is(Mood.ANGRY));

        moodValues.incAfraid(10);
        assertThat(moodValues.getMood(), is(Mood.AFRAID));
        moodValues.decAfraid(4);
        assertThat(moodValues.getMood(), is(Mood.NEUTRAL));
    }

    @Test
    public void lonely() throws Exception {
        moodValues = new MoodValues();
        moodValues.incLonely(1);
        assertThat(moodValues.getMood(), is(Mood.LONELY));
        moodValues.decLonely(2);
        assertThat(moodValues.getMood(), is(Mood.NEUTRAL));

        moodValues.incLonely(-1);
        assertThat(moodValues.getMood(), is(Mood.PRESSURED));
        moodValues.decLonely(1);
        assertThat(moodValues.getMood(), is(Mood.PRESSURED));

        moodValues.incLonely(10);
        assertThat(moodValues.getMood(), is(Mood.LONELY));
        moodValues.decLonely(4);
        assertThat(moodValues.getMood(), is(Mood.NEUTRAL));
    }

}