// TODO: Flere steder, hvor jeg skal caste Object til noget andet. Hvad er smart at gøre?

package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SurvivalRace {

    QueueMaker qm = new QueueMaker();

    /**
     * I survivalRace er det de stærkeste badeænder, der overlever...
     * Hver SurvivalDuck har en tilfældig styrkeværdi, som gør deres hold stærkere
     * For hver generation ryger det svageste hold
     * Der skal nok liiige lidt mere til, før det bliver interessant...
     */
    public void survivalRace(int numberOfQueues, int numberOfDucks) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<Queue> queueArrayList = qm.makeQueuesOfAnyObject(SurvivalDuck.class, numberOfQueues, numberOfDucks);
        qm.addQueueStrengths(queueArrayList);
        Collections.sort(queueArrayList); // Sorting by queueStrength thanks to Comparable<T>, yay
        Scanner sc = new Scanner(System.in);

        System.out.println("Alright, survivalists, it's time to dance\n");

        // Racing
        while (queueArrayList.size() > 1) {
            System.out.println("With " + queueArrayList.size() + " teams left...\n");
            System.out.println("Oh... The ducks on team " + queueArrayList.get(0).getId() + " could only muster a total strength of " + queueArrayList.get(0).getQueueStrength() +
                    "\n\nLet's say goodbye, then!");
            queueArrayList.remove(0);
            String input = sc.nextLine();
        }

        // Winning
        if (queueArrayList.size() == 1) {
            System.out.println("Oh, wow...\nWith a collective strength of " + queueArrayList.get(0).getQueueStrength() + "...");
            System.out.println("Team " + queueArrayList.get(0).getId() + " wins!!\n");
            System.out.println("So, uh... Wanna play again?");
            String input = sc.nextLine();
            survivalRace(numberOfQueues, numberOfDucks); // TODO: Options
        }
    }
}