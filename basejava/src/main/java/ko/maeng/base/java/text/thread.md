### Thread

1. Thread를 상속받는 클래스는 독립적인 흐름을 갖는 하나의 스레드로서의 역할을 수행한다.
2. run()는 main() 스레드와 별개의 독립적인 실행 흐름을 갖는다. 또는 별개의 움직이는 실행 위치이다.
3. run()만하면 단순히 메서드 호출에 불과하다. -> 독립적인 실행을 하는 스레드가 아니다.
4. Runnable 인터페이스에는 start()라는 메서드가 없다. 그래서 Runnable을 매개변수로 갖는 Thread 생성자를 사용한다.

#### 스레드의 우선 순위

스레드는 우선순위라는 속성(멤버변수)을 가지고 있는데, 이 우선순위의 값에 따라 스레드가 얻는 실행시간이 달라진다. 
스레드가 수행하는 작업의 중요도에 따라 스레드의 우선순위를 서로 다르게 지정하여 특정 스레드가 더 많은 작업시간을 갖도록 할 수 있다.

예를 들어 파일 전송기능이 있는 메신저의 경우, 파일다운로드를 처리하는 쓰레드보다, 
채팅 내용을 전송하는 쓰레드의 우선순위가 더 높아야 사용자가 채팅을 하는데 불편함이 없을 것이다. 대신 파일다운로드 작업에 걸리는 시간은 더 길어질 것이다.

```java
void setPriority(int newPriority): 스레드의 우선순위를 지정한 값으로 변경한다.

int getPriority(): 쓰레드의 우선순위를 반환한다.

public static final int MAX_PRIORITY = 10 // 최대 우선 순위

public static final int MIN_PRIORITY = 1 // 최소 우선 순위

public static final int NORM_PRIORITY = 5 //보통 우선 순위
```

쓰레드가 가질 수 있는 우선순위의 범위는 1~10이며 숫자가 높을 수록 우선순위가 높다.

그러나 우선수위의 높고 낮음은 절대적인 것이 아니라 상대적인 것임에 주의하자.

한가지 더 알아둘 것은 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속 받는다는 것이다. 
main 메서드를 수행하는 쓰레드는 우선순위가 5이므로 main 메서드 내에서 생성하는 쓰레드의 우선순위는 자동적으로 5가 된다.

```java
class ThreadPriority {
	public static void main(String args[]) {
		A th1 = new A();
		B th2 = new B();

		th1.setPriority(4); // defalut 우선순위 5
		th2.setPriority(7);

		System.out.println("Priority of th1(-) : " + th1.getPriority() );
		System.out.println("Priority of th2(|) : " + th2.getPriority() );

		th1.start();
		th2.start();
	}
}

class A extends Thread {
	public void run() {
		for(int i=0; i < 300; i++) {
			System.out.print("-");
			for(int x=0; x < 10000000; x++);
		}
	}
}

class B extends Thread {
	public void run() {
		for(int i=0; i < 300; i++) {
			System.out.print("|");
			for(int x=0; x < 10000000; x++);
		}
	}	
}
```
주의 사항은 스레드를 실행하기 전에만 우선 순위를 변경할 수 있다.