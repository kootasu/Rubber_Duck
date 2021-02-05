// Old Race class
// Has some methods that I removed elsewhere because they seemed obsolete

package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class OldRace {

    public ArrayList<Queue> makeQueuesOfAnyObject(Class c, int numberOfQueues, int numberOfObjects) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList<Queue> queueArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfQueues; i++) { // Put numberOfQueues queues into ArrayList
            queueArrayList.add(new Queue());
        }
        for (Queue queue : queueArrayList) { // Enqueue numberOfObjects in each queue
            for (int i = 0; i < numberOfObjects; i++) {
                Object o = c.getDeclaredConstructor().newInstance();
                queue.enqueue(o);
            }
        }
        return queueArrayList;
    }

    public ArrayList<Queue> makeDuckQueues(int numberOfQueues, int numberOfDucks) {
        ArrayList<Queue> queueArrayList = new ArrayList<>();
        // Put numberOfQueues queues into ArrayList
        for (int i = 0; i < numberOfQueues; i++) {
            queueArrayList.add(new Queue());
        }
        // Enqueue numberOfDucks ducks in each queue
        for (Queue queue : queueArrayList) {
            for (int i = 0; i < numberOfDucks; i++) {
                queue.enqueue((new RubberDuck()));
            }
        }
        return queueArrayList;
    }

    public ArrayList<Queue> addQueueStrengths(ArrayList<Queue> queueArrayList){
        for (Queue queue : queueArrayList) {
            double totalQueueStrength = 0;
            for (int i = 0; i < queue.count(); i++) { // For every duck in this queue, add their strength to their queue's strength
                SurvivalDuck sd = (SurvivalDuck) queue.peekAnywhere(i); // Casting Object-type object as SurvivalDuck
                totalQueueStrength += sd.getStrength();
            }
            queue.setQueueStrength(totalQueueStrength);
        }
        return queueArrayList;
    }

    public ArrayList<Queue> makeSurvivalDuckQueues(int numberOfQueues, int numberOfDucks) {
        ArrayList<Queue> queueArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfQueues; i++) { // Put numberOfQueues queues into ArrayList
            queueArrayList.add(new Queue());
        }
        for (Queue queue : queueArrayList) { // Enqueue numberOfDucks ducks in each queue
            double totalQueueStrength = 0;
            for (int i = 0; i < numberOfDucks; i++) {
                queue.enqueue((new SurvivalDuck()));
            }
            for (int i = 0; i < queue.count(); i++){ // For every duck in this queue, add their strength to their queue's strength
                SurvivalDuck sd = (SurvivalDuck) queue.peekAnywhere(i); // Casting Object-type object as SurvivalDuck
                totalQueueStrength += sd.getStrength();
            }
            queue.setQueueStrength(totalQueueStrength);
        }
        return queueArrayList;
    }

    // Wroom wroom!
    public void race(int numberOfQueues, int numberOfDucks) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //ArrayList<Queue> queueArrayList = makeDuckQueues(numberOfQueues, numberOfDucks); // Now I have all my ducks, yay
        ArrayList<Queue> queueArrayList = makeQueuesOfAnyObject(RubberDuck.class, numberOfQueues,numberOfDucks);
        Random random = new Random(); // For finding my random numbers
        int ticks = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Ladyducks and duckmen, it's finaaally time to race!\n");

        // Let's race
        while (queueArrayList.size() > 1) {
            System.out.println("Aaaand, we're at " + ticks + " ticks!\n");

            // Removing a random queue
            int randomQueueInt = random.nextInt(queueArrayList.size()); // Storing random index
            Queue randomQueue = queueArrayList.get(randomQueueInt); // Here's our unlucky queue
            System.out.println("Uh oh, looks like Team " + randomQueue.getId() + " is already done for! Quack you later!\n");
            queueArrayList.remove(randomQueueInt); // Goodbye queue no. randomQueueInt

            // Dequeue a duck from every queue
            for (int i = 0; i < queueArrayList.size(); i++) {
                queueArrayList.get(i).dequeue();
            }

            // If the race is done
            if (queueArrayList.size() == 1) {
                RubberDuck winner = (RubberDuck) queueArrayList.get(0).peek();
                System.out.println("Well, well, well – looks like we have a winner! It's...\n");
                System.out.println("The duck known as " + winner.getId() + " from Team " + queueArrayList.get(0).getId() + "!\n");
                System.out.println("So, uh... Wanna play again?");
                String input = sc.nextLine();
                race(numberOfQueues, numberOfDucks); // TODO: Options
            }

            // Or if the race goes on
            else {
                System.out.println("Quack quack – AND every team lose a duck!\n");
                System.out.println("Teams left: " + queueArrayList.size() + "\n");
                int ducksLeft = queueArrayList.size() * queueArrayList.get(0).count(); // Not really counting...
                System.out.println("Ducks left: " + ducksLeft + "\n");
                System.out.println("Waiting for time to move forward... ... ...\n");
                System.out.println("(Psst, you need to hit ENTER)\n");
                String input = sc.nextLine();
                ticks++;
            }
        }
    }

    /**
     * I survivalRace er det de stærkeste badeænder, der overlever...
     * Hver SurvivalDuck har en tilfældig styrkeværdi, som gør deres hold stærkere
     * For hver generation ryger det svageste hold
     * Der skal nok liiige lidt mere til, før det bliver interessant...
     */
    public void survivalRace(int numberOfQueues, int numberOfDucks) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<Queue> queueArrayList = makeQueuesOfAnyObject(SurvivalDuck.class, numberOfQueues, numberOfDucks);
        addQueueStrengths(queueArrayList);
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