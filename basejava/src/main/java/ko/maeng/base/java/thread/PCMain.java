package ko.maeng.base.java.thread;

public class PCMain {
	public static void main(String[] args) {
		Table table = new Table(100);
		new ProducerThread(table).start();
		new ConsumerThread(table).start();
	}
}
