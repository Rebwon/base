package ko.maeng.base.java.tutorials.multithread;

public class ThreadExam {
    public static void main(String[] args) {
        Runnable r = new A();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }
}

class Account{
    int balance = 1000;

    // synchronized 키워드를 사용해 출금 시 영향을 받지 않게 함.
    public synchronized void withDraw(int money) {
        //synchronized (this) { // 객체에 lock을 거는 법.
            if(balance >= money) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                balance -= money;
            }
        //}
    }
}

class A implements Runnable {
    Account account = new Account();

    @Override
    public void run() {
        while(account.balance > 0) {
            int money = (int) (Math.random() * 3 + 1) * 100;
            account.withDraw(money);
            System.out.println("balance : " + account.balance);
        }
    }
}
