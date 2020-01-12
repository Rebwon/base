package ko.maeng.base.java.tutorials.multithread;

public class Task implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println("thread01 : " + i);
        }
    }
}
