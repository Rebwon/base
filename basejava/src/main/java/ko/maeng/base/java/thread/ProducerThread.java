package ko.maeng.base.java.thread;

import lombok.SneakyThrows;

public class ProducerThread extends Thread {
	private static int id = 0;
	Table table;

	public ProducerThread(Table table) {
		this.table = table;
	}

	@SneakyThrows
	@Override
	public void run() {
		while(true) {
			Thread.sleep(1000);
			String packet = "No: " + nextId();
			table.put(packet); 	// 큐에 추가
		}
	}

	private static synchronized int nextId() {
		return id++;
	}
}
