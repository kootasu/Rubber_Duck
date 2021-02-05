package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SurvivalRaceEvolved {

    QueueMaker qm = new QueueMaker();

    // I hver generation:
    // Køer, hvis samlede styrke er lig med eller mindre end minimumQueueStrength, ryger ud
    // Køer, hvis samlede styrker er lig med eller over strengthToReproduce, reproducerer sig
    // Hver and muterer lidt – bliver stærkere eller svagere
    // Den samlede styrke for hver kø opdateres
    // TODO: Måske tilføje noget med overbefolkning
    public void survivalRaceEvolved(int numberOfQueues, int numberOfDucks, double minimumQueueStrength, double unluckyMutation, double luckyMutation, double strengthToReproduce) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<Queue> queueArrayList = qm.makeQueuesOfAnyObject(SurvivalDuck.class, numberOfQueues, numberOfDucks);
        ArrayList<Queue> newQueuesArrayList = new ArrayList<>();
        qm.addQueueStrengths(queueArrayList); // queueStrength needs to be not zero to begin
        Scanner sc = new Scanner(System.in);
        int reproductionCounter = 0;
        int ticks = 0;

        // Playing
        while (queueArrayList.size() > 1) {
            Collections.sort(queueArrayList); // Sorting by queueStrength thanks to Comparable<T>, yay
            System.out.println("At generation " + ticks + " we have " + queueArrayList.size() + " teams\n\n" +
                    "Remember, your team's gotta have a combined strength of at least " + minimumQueueStrength +
                    " to survive\n\n" +
                    "In order to reproduce, your team needs a strength of " + strengthToReproduce + "\n\n" +
                    "Let's have a look at our teams\n");
            for (Queue queue : queueArrayList) {
                System.out.println("Team " + queue.getId() + " has a strength of " + queue.getQueueStrength());
            }

            queueArrayList.removeIf(queue -> queue.getQueueStrength() < minimumQueueStrength); // Removing all queues with a strength below something

            for (Queue queue : queueArrayList){
                if (queue.getQueueStrength() > strengthToReproduce) {
                    reproductionCounter++; // Counting queues with large enough strengths to reproduce
                }
            }
            ArrayList<Queue> newQueueArrayList = qm.makeQueuesOfAnyObject(SurvivalDuck.class, reproductionCounter, numberOfDucks);
            qm.addQueueStrengths(newQueueArrayList);
            for (Queue queue : newQueueArrayList){
                queueArrayList.add(queue);
            }
            reproductionCounter = 0; // Resetting this counter

            mutate(unluckyMutation, luckyMutation, queueArrayList); // All ducks mutate

            qm.addQueueStrengths(queueArrayList); // queueStrength needs to be updated after mutation

            ticks++;
            System.out.println("\nThe ducks are mutating... Hit enter to see what happens next\n");
            String input = sc.nextLine();
            }

        // Game over
        if (queueArrayList.size() == 0) {
            System.out.println("\nI guess it had to happen – all teams are gone\n");;
            System.out.println("So, uh... Wanna play again?");
            String input = sc.nextLine();
            survivalRaceEvolved(numberOfQueues, numberOfDucks, minimumQueueStrength, unluckyMutation, luckyMutation, strengthToReproduce); // TODO: Options
        }
    }

    private void mutate(double unluckyMutation, double luckyMutation, ArrayList<Queue> queueArrayList) {
        Random random = new Random();
        for (Queue queue : queueArrayList) { // Now each duck mutates
            for (int i = 0; i < queue.count(); i++){
                SurvivalDuck sd = (SurvivalDuck) queue.peekAnywhere(i);
                sd.setStrength(sd.getStrength() + (unluckyMutation + (luckyMutation) * random.nextDouble())); // Changes to the duck's strength
            }
        }
    }
}