package ko.maeng.oop.ladder.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ko.maeng.oop.ladder.core.NatualNumber;

class LadderRunnerTest {

	@Test
	public void generate_라인이_없는경우() {
		Row[] rows = new Row[3];
		for(int i=0; i<rows.length; i++) {
			rows[i] = new Row(new NatualNumber(3));
		}
		String result = LadderRunner.generate(rows, Position.create(1, 1));
		assertEquals("0* 0 0 \n0 0 0 \n0 0 0 \n", result);
	}

	@Test
	public void generate_라인이_있는경우() {
		Row[] rows = new Row[3];
		for(int i=0; i<rows.length; i++) {
			rows[i] = new Row(new NatualNumber(3));
		}
		rows[0].drawLine(new NatualNumber(1));
		String result = LadderRunner.generate(rows, Position.create(1, 1));
		assertEquals("1* -1 0 \n0 0 0 \n0 0 0 \n", result);
	}
}