package ko.maeng.base.java.etc.tutorials.multithread;

public class ThreadExam2 {
    public static void main(String[] args) {
        K kt = new K();
        kt.start();
    }
}

class K extends Thread {
    private int x = 100;

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x += x;
    }

    @Override
    public void run() {
        synchronized (this) {
            setX(200);  // 300 + 200
            System.out.println("x : " + getX());    // 500
        }
    }
}
