// TODO: I makeQueuesOfAnyObject() prøver jeg at bruge en klasse som et argument – god/dårlig ide?

package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class QueueMaker {

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
}
