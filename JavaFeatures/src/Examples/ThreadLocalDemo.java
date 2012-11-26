package Examples;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Shaun
 */
public class ThreadLocalDemo {

    // Array list to keep thread references in
    ArrayList<Thread> threadArrray = new ArrayList<Thread>();

    // The thread local varibale that each thread will use
    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    Random randomNumGen = new Random();

    public ThreadLocalDemo() {
    }

    private void createThreads() {

        // Loop to create 10 threads
        for (int i = 0; i < 10; i++) {
            final int copyOfI = i;
            Thread thread = new Thread(new Runnable() {

                // Name each thread something unique
                String threadName = "Thread " + copyOfI;

                @Override
                public void run() {

                    // Generate random number and set the threadLocal to that number
                    int randomNumber = randomNumGen.nextInt(100);
                    System.out.println(threadName + " set it's threadLocal to: " + randomNumber);
                    threadLocal.set(randomNumber);

                    // Sleep for a bit
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        System.out.println("Uh Oh!");
                    }

                    // Get the threadLocal variable to prove other threads havn't modified it
                    System.out.println(threadName + " threadLocal is now: " + threadLocal.get());
                }
            });

            threadArrray.add(thread);
        }
    }

    private void runThreads() {
        for (Thread thread : threadArrray) {
            thread.start();
        }
    }

    public static void main(String args[]) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        demo.createThreads();
        demo.runThreads();
    }
}
