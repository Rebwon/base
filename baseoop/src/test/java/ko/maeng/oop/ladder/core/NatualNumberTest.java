package ko.maeng.oop.ladder.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NatualNumberTest {
	@Test
	public void crate() {
		NatualNumber natualNumber = new NatualNumber(1);
		assertEquals(1, natualNumber.getNumber());
	}

	@Test
	public void createWhenUnderZero() {
		assertThrows(IllegalArgumentException.class, () ->
			new NatualNumber(0));
	}

	@Test
	public void toArrayIndex() {
		NatualNumber number = new NatualNumber(3);
		assertEquals(2, number.toArrayIndex());
	}

	@Test
	void createFromArrayIndex() {
		NatualNumber natualNumber = NatualNumber.createFromArrayIndex(1);
		assertEquals(new NatualNumber(2), natualNumber);
	}

	@Test
	void multiply() {
		NatualNumber natualNumber = new NatualNumber(2);
		NatualNumber actual = natualNumber.multiply(new NatualNumber(3));
		assertEquals(new NatualNumber(6), actual);
	}
}