package ko.maeng.oop.boardoop.common;

public class StringUtils {
	private StringUtils() {}

	public static String validate(String value) {
		if(value.isBlank() || value.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return value;
	}
}
