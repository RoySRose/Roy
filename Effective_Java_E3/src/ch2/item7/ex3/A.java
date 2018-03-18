package ch2.item7.ex3;
/**
 * @author Roy Kim
 */

// Java program to illustrate Asynchronous callback
class A implements OnGeekEventListener {

    @Override
    public void onGeekEvent()
    {
        System.out.println("Performing callback after Asynchronous Task");
        // perform some routine operation
    }
    // some class A methods
}