package Examples;

/**
 *
 * @author Shaun
 */
public class MultipleDemo {

    public static void main(String args[]) {
        MultipleDemo demo = new MultipleDemo();
        demo.runArgsDemo("hello", "shauney", "javaProgramming");
        demo.runEnumDemo();
    }

    public void runArgsDemo(String... args) {
        for (String string : args) {
            System.out.println("*" + string + "*" + " was supplied as an argument");
        }
    }

    public void runEnumDemo() {
        System.out.println("Create a happy cat");
        Cat kittyCat = Cat.Happy;

        if (kittyCat == Cat.Grumpy || kittyCat == Cat.Angry) {
            // Don't pet
            System.out.println("Uh Oh! its angry or grumpy!");
        }
        else {
            // Pet
            System.out.println("Now pet it!");
        }
    }

}

enum Cat {
    Angry,
    Grumpy,
    Happy
}
