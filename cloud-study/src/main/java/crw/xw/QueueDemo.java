package crw.xw;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class QueueDemo {
  public static void main(String[] args) {
    Queue<Runnable> taskQueue = new ConcurrentLinkedDeque<>();
    ((ConcurrentLinkedDeque<Runnable>) taskQueue).push(Thread.currentThread());
    taskQueue.poll();
    System.out.println(taskQueue);
  }

  public void sayHello() {
    System.out.println("hello crw !!!");
  }
}
