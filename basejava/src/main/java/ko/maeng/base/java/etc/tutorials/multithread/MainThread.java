package ko.maeng.base.java.etc.tutorials.multithread;

public class MainThread {
    public static void main(String[] args) {
        // 방법 01 - 별도의 Runnable 인터페이스를 구현한 클래스를 만드는 방법
        // Runnable은 스레드가 실행할 작업내용을 가지고 있을 뿐. 실제 스레드는 아니다.
        // Runnable을 스레드 생성시 매개변수로 넘겨주어 thread 생성자를 호출한다.
        Runnable task = new Task();
        Thread thread01 = new Thread(task);

        // start() 실행 시 스레드 동작.
        thread01.start();

        //방법 02 - Runnable 인터페이스를 익명 객체를 매개값으로 사용하는 경우.
        Thread thread02 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println("thread02 : " + i);
                }
            }
        });

        // start
        thread02.start();

        // Main Thread
        for(int i=0; i<10; i++){
            System.out.println("MainThread : " + i);
        }
    }
}
