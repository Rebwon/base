package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ko.maeng.oop.ladder.core.NatualNumber;

public class PositionTest {
	@Test
	void create() {
		Position position1 = Position.create(3, 4);
		Position position2 = Position.create(new NatualNumber(3), new NatualNumber(4));

		assertEquals(position1, position2);
	}

	@Test
	void createFromArrayIndex() {
		Position position = Position.createFromArrayIndex(2, 3);
		Position position1 = Position.create(3, 4);
		assertEquals(position, position1);
	}
}
