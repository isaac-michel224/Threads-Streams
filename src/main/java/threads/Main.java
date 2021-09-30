package threads;

public class Main {


    //below is our entry point
    //it is how we start our application
    //your main method is in face also the main thread
    //the main thread as the ability to create additional threads


public static void main(String[] args) {

    // below we are instantiating a new thread
    // and passing in a new instance of threads.HelloRunnable
    // threads.HelloRunnable is of type Runnable which is one of the
    // objects that your thread will need to execute
    new Thread(new HelloRunnable()).start();

    //below we are instantiating a subclass of Thread
    //that is overriding its own run method
    new HelloThread().start();

    //bear in mind that Runnable is a functional interface
    //which means we can use it as a lambda expression
    //while at first this may seem a little hard to parse
    //it is arguably clearer, neater and more readable

    new Thread(() -> System.out.println("Hello from a Lambda runnable! ")).start();

    SynchronizedRGB color = new SynchronizedRGB(0,0,0, "Pitch Black");


    //this is our first statement
    int myColorInt = color.getRGB();

    //new Thread accesses color as a resource and invokes "set"
    new Thread(() -> color.set(255,255,255,"white"));

    //this is our second statement

    String myColorName = color.getName();

    System.out.println(color.getName());



    /*

    instead, we can group our two declarations in a synchronized code block!

    synchronized(color){
    int myColorInt = color.getRGB();
    String myColorNameInt = color.getName();
     */

    ImmutableRGB ImmutableRGB = new ImmutableRGB(red: 255, green: 255, blue: 255, name:"White");
    //bear in mind that invert() will produce a brande new object in the place of this
    //original immutableRGB
    new Thread(() ->System.out.println(immutableRGB.invert())).start();
}

}
