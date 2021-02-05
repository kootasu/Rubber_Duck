package com.company;

import java.lang.reflect.InvocationTargetException;

public class ReadySetGo {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        // Normal race
        Race race = new Race();
        //race.race(10,10); // Normal race

        // Survival race – queues are removed based on a strength value
        SurvivalRace survivalRace = new SurvivalRace();
        //survivalRace.survivalRace(10,10);

        // Funky little evolution game... Maybe try with some different parameters?
        SurvivalRaceEvolved sRE = new SurvivalRaceEvolved();
        //sRE.survivalRaceEvolved(10,10,30,-2,5,70);
        //sRE.survivalRaceEvolved(100,10,40,-3,3,60);

    }
}
