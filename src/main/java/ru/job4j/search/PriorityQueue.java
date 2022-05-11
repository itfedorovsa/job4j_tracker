package ru.job4j.search;

import java.util.LinkedList;

/**
 * The class describes the operation of the simple priority queue,
 * which operates according to the FIFO (first in - first out) principle.
 * @author Sergey Fedorov
 * @version 1.0
 */
public class PriorityQueue {
    /**
     * The task is stored in a collection of the LinkedList type.
     */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * The method accepts the request for input and adds it to the queue.
     * If there are 2 tasks with the same priority,
     * then they are distributed in the queue according to the FIFO principle.
     * @param task the task that is being added to the queue.
     */
    public void put(Task task) {
        var index = 0;
        for (Task element : tasks) {
            if (task.getPriority() < element.getPriority()) {
                break;
            }
            index++;
        }
        tasks.add(index, task);
    }

    /**
     * The method allows to get the first task in the queue
     * @return returns the task from the head of the queue or returns null if queue is empty
     */
    public Task take() {
        return tasks.poll();
    }
}
