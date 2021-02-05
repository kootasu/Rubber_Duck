package com.company;

import java.util.LinkedList;

public class Queue implements Comparable<Queue> {

    private int id;
    public static int queueCounter = 0;
    private double queueStrength;
    private boolean isDoomed;
    private LinkedList list;

    public Queue(){
        list = new LinkedList();
        id = queueCounter;
        queueCounter++;;
        queueStrength = 0;
    }

    // Returns true if empty queue
    public boolean isEmpty(){
        return (list.size() == 0);
    }

    // Adds element o to queue
    public void enqueue(Object o){
        list.add(o);
    }

    // Removes first element from queue unless empty
    public void dequeue(){
        if(!isEmpty()) {
            list.remove(0);
        }
        else System.out.println("Nothing in queue!");
    }

    // Returns first element in queue
    public Object peek(){
        return list.get(0);
    }

    // Returns element specified by index no.
    public Object peekAnywhere(int i) {return list.get(i);}

    // Count all elements in queue
    public int count(){
        int count = 0;
        for (Object o : list){
            count++;
        }
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQueueStrength() {
        return queueStrength;
    }

    public void setQueueStrength(double queueStrength) {
        this.queueStrength = queueStrength;
    }

    public boolean isDoomed() {
        return isDoomed;
    }

    public void setDoomed(boolean doomed) {
        isDoomed = doomed;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "list=" + list +
                '}';
    }

    @Override
    public int compareTo(Queue compareQueue) {
        int result = 0;
        if (this.getQueueStrength() == compareQueue.getQueueStrength()) {
            result = 0;
        } else if ((this.getQueueStrength() - compareQueue.getQueueStrength()) > 0) {
            result = 1;
        } else if ((this.getQueueStrength() - compareQueue.getQueueStrength()) < 0) {
            result = -1;
        }
        return result;
    }
}
