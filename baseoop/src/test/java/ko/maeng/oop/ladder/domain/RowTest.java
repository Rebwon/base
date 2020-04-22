package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ko.maeng.oop.ladder.core.NatualNumber;

class RowTest {
	Row row;

	@BeforeEach
	public void setUp() {
		row = new Row(new NatualNumber(3));
	}

	@Test
	public void startPositionWhenOverNoOfPersons() {
		assertThrows(IllegalArgumentException.class, () ->
			row.drawLine(new NatualNumber(3)));
	}

	@Test
	public void drawLineWhenAlreadyDrawingPoint() {
		assertThrows(IllegalArgumentException.class, () -> {
			row.drawLine(new NatualNumber(1));
			row.drawLine(new NatualNumber(2));
		});
	}

	@Test
	public void moveWhenNoLine(){
		NatualNumber target = row.move(new Marker(1));
		assertEquals(1, target.getNumber());

		target = row.move(new Marker(2));
		assertEquals(2, target.getNumber());
	}

	@Test
	public void moveWhenLineLeft() {
		row.drawLine(new NatualNumber(2));

		NatualNumber target = row.move(new Marker(3));
		assertEquals(2, target.getNumber());
	}

	@Test
	public void moveWhenLineRight() {
		row.drawLine(new NatualNumber(2));

		NatualNumber target = row.move(new Marker(2));
		assertEquals(3, target.getNumber());
	}
}