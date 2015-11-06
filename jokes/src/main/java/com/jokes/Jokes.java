package com.jokes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Jokes {

    private static final String JOKE_DB_FILE = "jokes.db";
    private static Jokes sInstance;

    private String[] jokes;
    private int lInd = 0;

        public static Jokes getInstance() {
        if(sInstance == null)
            sInstance = new Jokes();
        return sInstance;
    }

    private Jokes() {
        initJokes();
    }

    private void initJokes() {
        try {
            /* Intialize the jokes database in memore by reading the JOKE_DB_FILE line by line */
            ArrayList < String > tmpHolder = new ArrayList<>();
            InputStream in = getClass().getResourceAsStream("/" + JOKE_DB_FILE);
            BufferedReader input = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = input.readLine()) != null) {
                tmpHolder.add(line);
            }
            if(jokes == null)
                jokes = new String[tmpHolder.size()];
            tmpHolder.toArray(jokes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getRandomJoke() {
        /* Provide a joke by randomly generating an index to retrieve from the jokes db */
        Random rnd = new Random();
        lInd = rnd.nextInt(jokes.length + 1);
        System.out.println("random num is: " + lInd + " max num: " + (jokes.length +1));

        return jokes[lInd];
    }
}
