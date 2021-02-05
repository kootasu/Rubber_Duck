// TODO: Flere steder, hvor jeg skal caste Object til noget andet. Hvad er smart at gøre?
// TODO: I makeQueuesOfAnyObject() prøver jeg at bruge en klasse som et argument – god/dårlig ide?

package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Race {

    QueueMaker qm = new QueueMaker();

    // Wroom wroom!
    public void race(int numberOfQueues, int numberOfDucks) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<Queue> queueArrayList = qm.makeQueuesOfAnyObject(RubberDuck.class, numberOfQueues,numberOfDucks);
        Random random = new Random(); // For finding my random numbers
        int ticks = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Ladyducks and duckmen, it's finaaally time to race!\n");

        // Let's race
        while (queueArrayList.size() > 1) {
            System.out.println("Aaaand, we're at " + ticks + " ticks!\n");

            // Removing a random queue
            int randomQueueInt = random.nextInt(queueArrayList.size()); // Storing random index
            Queue randomQueue = queueArrayList.get(random.nextInt(queueArrayList.size())); // Here's our unlucky queue
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
                ticks++;
                String input = sc.nextLine();
            }
        }
    }
}