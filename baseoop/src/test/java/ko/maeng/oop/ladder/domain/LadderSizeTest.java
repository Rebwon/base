package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ko.maeng.oop.ladder.core.NatualNumber;

public class LadderSizeTest {
	private LadderSize size;

	@BeforeEach
	void setUp() {
		size = LadderSize.create(3, 4);
	}

	@Test
	void getPositionOfHeight() {
		NatualNumber actual = size.getPositionOfHeight(new NatualNumber(5));
		assertEquals(new NatualNumber(2), actual);
	}

	@Test
	void getPositionOfPerson() {
		NatualNumber actual = size.getPositionOfPerson(new NatualNumber(5));
		assertEquals(new NatualNumber(1), actual);
	}

	@Test
	void convertTotalPositionsToPosition() {
		Position actual = size.getPosition(new NatualNumber(5));
		assertEquals(Position.create(2, 1), actual);
	}

	@Test
	void multipleOfPerson() {
		assertTrue(size.isMultipleOfPerson(new NatualNumber(8)));
		assertFalse(size.isMultipleOfPerson(new NatualNumber(7)));
	}
}
