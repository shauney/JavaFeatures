package Examples;

/**
 *
 * @author Shaun
 */
public class CompareDemo {

    public static void main(String args[]) {
        CompareDemo demo = new CompareDemo();
        demo.runCompareDemo();
    }

    public void runCompareDemo() {
        System.out.println("Set foo & bar to 1000");
        Integer foo = 1000;
        Integer bar = 1000;

        System.out.println("foo <= bar: " + (foo <= bar));
        System.out.println("foo >= bar: " + (foo >= bar));
        System.out.println("foo == bar: " + (foo == bar));
        System.out.println("\n");


        System.out.println("Set kay & jay to 42");
        Integer kay = 42;
        Integer jay = 42;

        System.out.println("kay <= jay: " + (kay <= jay));
        System.out.println("kay >= jay: " + (kay >= jay));
        System.out.println("kay == jay: " + (kay == jay));
    }
}
