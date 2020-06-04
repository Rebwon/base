package ko.maeng.base.java.generic;

import java.lang.reflect.Method;
import java.util.stream.Stream;

public class Generic2 {
	public static void main(String[] args) {
		Stream.of(Integer.class.getDeclaredMethods())
			.filter(m -> "compareTo".equals(m.getName()))
			.map(Method::toGenericString)
			.forEach(System.out::println);
	}

	// Generic 타입 파라미터가 포함된 메서드를 오버로딩할 경우,
	// Bridge 메서드를 자동으로 추가하여 레거시 코드와의 호환성을 유지한다.
}
