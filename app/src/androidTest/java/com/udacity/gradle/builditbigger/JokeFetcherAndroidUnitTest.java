package com.udacity.gradle.builditbigger;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.callbacks.JokeTaskCallback;
import com.udacity.gradle.builditbigger.tasks.JokeFetchTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class JokeFetcherAndroidUnitTest {

    @Test
    public void jokeFetcher_GetsNonEmptyString() {
        JokeTaskCallback jokeTaskCallback = new JokeTaskCallback() {
            @Override
            public void showJoke(String joke) {
                assertNotNull(joke);
            }
        };
        new JokeFetchTask(jokeTaskCallback).execute();
    }
}