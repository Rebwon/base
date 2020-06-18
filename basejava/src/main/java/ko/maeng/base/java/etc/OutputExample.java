package ko.maeng.base.java.etc;

public class OutputExample {
	public static void main(String[] args) {
		method(null);
		Integer num1 = 100;
		Integer num2 = 100;

		if(num1 == num2) {
			// Integer 클래스는 equalsAndHashCode가
			// 재정의되어 있기 때문에, 값의 동등 비교는 무조건 참이다.
			System.out.println("num1 == num2");
		}else{
			System.out.println("num1 != num2");
		}
	}

	private static void method(Object o) {
		System.out.println("Object method");
	}

	// null은 객체가 아니다, 하지만 모든 객체 참조 유형에는
	// null을 할당할 수 있다. Java 컴파일러는 가장 구체적인 매개 변수를
	// 사용하여 오버로드된 메서드를 호출하도록 선택한다.
	// Object보다는 String이 더 구체적인 클래스이기 때문에
	// String의 메서드를 호출한다.
	private static void method(String s) {
		System.out.println("String method");
	}
}
