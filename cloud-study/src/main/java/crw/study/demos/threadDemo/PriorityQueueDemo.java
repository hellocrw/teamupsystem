package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo implements Comparator<Integer> {

    private static final Logger logger = LoggerFactory.getLogger(PriorityQueueDemo.class);

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(5, new PriorityQueueDemo());
        priorityQueue.add(5);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(4);
        priorityQueue.add(6);
        priorityQueue.add(10);
        priorityQueue.add(8);
        while (priorityQueue.size() > 0) {
            logger.info("get data : " + priorityQueue.remove());
        }
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 > o2) {
            return 1;
        } else if (o1 < o2) {
            return -1;
        } else {
            return 0;
        }
    }
}
