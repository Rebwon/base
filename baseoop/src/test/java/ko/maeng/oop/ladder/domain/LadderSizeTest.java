package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ko.maeng.oop.ladder.core.NatualNumber;

public class LadderSizeTest {
	@Test
	void getPositionOfHeight() {
		LadderSize size = LadderSize.create(3, 4);
		NatualNumber actual = size.getPositionOfHeight(new NatualNumber(5));
		assertEquals(new NatualNumber(2), actual);
	}

	@Test
	void getPositionOfPerson() {
		LadderSize size = LadderSize.create(3, 4);
		NatualNumber actual = size.getPositionOfPerson(new NatualNumber(5));
		assertEquals(new NatualNumber(1), actual);
	}

	@Test
	void convertTotalPositionsToPosition() {
		LadderSize size = LadderSize.create(3, 4);
		Position actual = size.getPosition(new NatualNumber(5));
		assertEquals(Position.create(2, 1), actual);
	}
}
