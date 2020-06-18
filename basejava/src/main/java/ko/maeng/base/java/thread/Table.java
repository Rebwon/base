package ko.maeng.base.java.thread;

public class Table {
	private final String[] buffer;
	private int tail;
	private int head;
	private int count;

	public Table(int count) {
		this.buffer = new String[count];
		this.head = 0;
		this.tail = 0;
		this.count = 0;
	}

	public synchronized void put(String packet) throws InterruptedException {
		while(count >= buffer.length) {	// 버퍼가 차면 대기
			wait();
		}
		buffer[tail] = packet;	// 후입
		tail = (tail+1) % buffer.length;	// Circular 큐라서 tail 위치 바뀜
		count++;
		notifyAll();	// 버퍼에 먼가가 들어갔으니 take 해도 된다고 이벤트 날림
	}

	public synchronized String take() throws InterruptedException {
		while(count <= 0) {		// 버퍼에 아무것도 없으면 대기
			wait();
		}
		String packet = buffer[head];	// 선출
		head = (head+1) % buffer.length; // Circular 큐라서 head 위치 바뀜
		count--;
		notifyAll();	// 버퍼에서 하나를 가져갔으니 put해도 된다고 이벤트 날림
		return packet;
	}
}
