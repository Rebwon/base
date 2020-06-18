package ko.maeng.base.java.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Table {
	private final BlockingQueue<String> buffer;

	public Table(int count) {
		this.buffer = new ArrayBlockingQueue<String>(10);
	}

	public void put(String packet) throws InterruptedException {
		Thread.sleep(1000);
		buffer.put(packet);
	}

	public String take() throws InterruptedException {
		String packet = buffer.take();
		return packet;
	}
}
