package ko.maeng.base.java.thread;

import lombok.SneakyThrows;

public class ConsumerThread extends Thread {
	private final Table table;

	public ConsumerThread(Table table) {
		this.table = table;
	}

	@SneakyThrows
	@Override
	public void run() {
		while(true) {
			String packet = table.take();	// 큐에서 가져옴
			System.out.println("consumer: " + packet);
		}
	}
}
