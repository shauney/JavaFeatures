package Examples;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Shaun
 */
public class AtomicDemo {

    // "Data" for each test
    private AtomicInteger atomicData = new AtomicInteger(1);
    private int syncData = 1;

    private final Object lockObject = new Object();
    ArrayList<Thread> threadArrray = new ArrayList<Thread>();

    public AtomicDemo() {
    }

    public void createAtomicThreads() {

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        boolean success = false;

                        while (!success) {
                            // Get the data to compute
                            int input = atomicData.get();

                            // "Compute" the data
                            int output = input + 1;
                            try {
                                Thread.sleep(4);
                            } catch (InterruptedException ex) {
                                System.out.println("Uh Oh!");
                            }

                            // Set the data only if another thread has not modified it
                            success = atomicData.compareAndSet(input, output);
                        }
                    }
                }
            });

            threadArrray.add(thread);
        }
    }

    public void runAtomicDemo() throws InterruptedException {

        createAtomicThreads();
        long startTime = System.currentTimeMillis();

        for (Thread thread : threadArrray) {
            thread.start();
        }
        for (Thread thread : threadArrray) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Atomic execution took: " + (endTime - startTime) + "ms");

        threadArrray.clear();
    }

    public void createSyncThreads() {

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {

                        synchronized(lockObject) {
                            // Get the data to compute
                            int input = syncData;

                            // "Compute" the data
                            int output = input + 1;
                            try {
                                Thread.sleep(4);
                            } catch (InterruptedException ex) {
                                System.out.println("Uh Oh!");
                            }

                            // Set the data
                            syncData = output;
                        }
                    }
                }
            });

            threadArrray.add(thread);
        }
    }

    public void runSyncDemo() throws InterruptedException {

        createSyncThreads();
        long startTime = System.currentTimeMillis();

        for (Thread thread : threadArrray) {
            thread.start();
        }
        for (Thread thread : threadArrray) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Syncronized execution took: " + (endTime - startTime) + "ms");

        threadArrray.clear();
    }

    public static void main(String args[]) {
        AtomicDemo demo = new AtomicDemo();

        try {
            demo.runAtomicDemo();
            demo.runSyncDemo();
        }
        catch (Exception ex){
            System.out.println("Something went wrong!");
        }
    }
}
