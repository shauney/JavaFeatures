package Examples;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Shaun
 */
public class ForkJoin extends RecursiveAction {

    private final int threashold = 50;
    private int[] data;
    private int startIndex;
    private int endIndex;

    public ForkJoin(int[] data, int start, int end) {
        this.data = data;
        this.startIndex = start;
        this.endIndex = end;
    }


    private int complicatedCalculation(int number) {
        try {
            Thread.sleep(4);
        } catch (InterruptedException ex) {
        }
        return number;
    }

    @Override
    public void compute() {
        int length = endIndex - startIndex;

        if ((length) < threashold){
            System.out.println(Thread.currentThread().getName() + " - Computing task directly");
            for (int i = startIndex; i < endIndex; i++) {
                complicatedCalculation(data[i]);
            }
            return;
        }

        int split = length / 2;

        System.out.println("Task too big, splitting into two tasks!");
        invokeAll(new ForkJoin(data, startIndex, startIndex + split),
                new ForkJoin(data, startIndex + split, endIndex - split));
    }

    public static void main(String[] args) {
        int[] data = new int[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        System.out.println("######## Running Large Task ########");
        System.out.println("");

        ForkJoin demo = new ForkJoin(data, 0, data.length);
        ForkJoinPool forkPool = new ForkJoinPool();

        forkPool.invoke(demo);


        int[] data2 = new int[40];
        for (int i = 0; i < data2.length; i++) {
            data2[i] = i;
        }

        System.out.println("######## Running Small Task ########");
        System.out.println("");

        ForkJoin demo2 = new ForkJoin(data2, 0, data2.length);
        ForkJoinPool forkPool2 = new ForkJoinPool();

        forkPool.invoke(demo2);
    }
}
