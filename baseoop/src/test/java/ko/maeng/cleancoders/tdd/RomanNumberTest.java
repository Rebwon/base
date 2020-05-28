package ko.maeng.cleancoders.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RomanNumberTest {
	@Test
	void convertRomanNumber() {
		assertEquals("I", convert(1));
		assertEquals("II", convert(2));
		assertEquals("III", convert(3));
		assertEquals("IV", convert(4));
		assertEquals("V", convert(5));
		assertEquals("VI", convert(6));
	}

	public String convert(int number) {
		return null;
	}
}
