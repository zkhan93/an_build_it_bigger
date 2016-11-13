package com.example;

import com.example.model.Joke;

public class Joker {
    private String[] jokes = {
            "this is a joke", "this is not a funny joke", "this is a funny joke", "pathetic joke"
    };
    private int index = 0;

    public Joke getJoke() {
        if (index > 3)
            index = 0;
        return new Joke(jokes[index++]);
    }
}
