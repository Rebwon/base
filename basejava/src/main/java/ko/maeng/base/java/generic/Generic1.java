package ko.maeng.base.java.generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Generic1 {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
		Integer max = max(list);
		System.out.println(max);
	}

	// 아래 메서드는 compareTo에서 컴파일 에러가 발생한다.
	// 그 이유는 <T>라는 Generic 타입이 존재한다는 것만 지정했을 뿐,
	// 어떠한 타입이든 지정되어도 상관 없다라는 뜻이기 때문에
	// Comparable<T>를 구현한 타입만 지정되도록 바꾸어야 한다.
	/*public static <T> T max(Collection<T> cols) {
		return cols.stream().max((o1, o2) -> o1.compareTo(o2)).get();
	}*/

	// 타입 경계를 지정했기 때문에 사용은 가능하지만,
	// Get and Put 원리를 적용해서 보완해야 한다.
	/*public static <T extends Comparable<T>> T max(Collection<T> cols){
		return cols.stream().max((o1, o2) -> o1.compareTo(o2)).get();
	}*/

	// 메서드 레퍼런스까지 적용한 최종 구현.
	public static <T extends Comparable<? super T>> T max(Collection<? extends T> cols){
		return cols.stream().max(T::compareTo).get();
	}
}
