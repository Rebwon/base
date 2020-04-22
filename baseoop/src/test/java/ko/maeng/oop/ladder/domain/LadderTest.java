package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ko.maeng.oop.ladder.core.NatualNumber;

class LadderTest {

	@Test
	public void runWhenMultiRows() {
		// 1 -1 0 0
		// 0 1 -1 0
		// 0 0 1 -1
		Ladder ladder = new Ladder(new NatualNumber(3), new NatualNumber(4));
		ladder.drawLine(new NatualNumber(1), new NatualNumber(1));
		ladder.drawLine(new NatualNumber(2), new NatualNumber(2));
		ladder.drawLine(new NatualNumber(3), new NatualNumber(3));

		assertEquals(new Marker(4), ladder.run(new Marker(1)));
		assertEquals(new Marker(1), ladder.run(new Marker(2)));
		assertEquals(new Marker(2), ladder.run(new Marker(3)));
		assertEquals(new Marker(3), ladder.run(new Marker(4)));
	}

	@Test
	public void generate_라인이_없는경우() {
		Row[] rows = new Row[3];
		for(int i=0; i<rows.length; i++) {
			rows[i] = new Row(new NatualNumber(3));
		}
		String result = Ladder.generate(rows, Position.create(1, 1));
		assertEquals("0* 0 0 \n0 0 0 \n0 0 0 \n", result);
	}

	@Test
	public void generate_라인이_있는경우() {
		Row[] rows = new Row[3];
		for(int i=0; i<rows.length; i++) {
			rows[i] = new Row(new NatualNumber(3));
		}
		rows[0].drawLine(new NatualNumber(1));
		String result = Ladder.generate(rows, Position.create(1, 1));
		assertEquals("1* -1 0 \n0 0 0 \n0 0 0 \n", result);
	}
}