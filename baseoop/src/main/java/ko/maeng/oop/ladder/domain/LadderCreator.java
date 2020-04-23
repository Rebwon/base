package ko.maeng.oop.ladder.domain;

import ko.maeng.oop.ladder.core.NatualNumber;

class LadderCreator {
	private Row[] rows;

	LadderCreator(NatualNumber height, NatualNumber nthOfPerson) {
		rows = new Row[height.getNumber()];

		for(int i=0 ;i<height.getNumber(); i++) {
			rows[i] = new Row(nthOfPerson);
		}
	}

	void drawLine(NatualNumber height, NatualNumber startPosition) {
		if(isOverHeight(height)) {
			throw new IllegalArgumentException();
		}
		rows[height.toArrayIndex()].drawLine(startPosition);
	}

	private boolean isOverHeight(NatualNumber height) {
		return height.toArrayIndex() > rows.length - 1;
	}

	public Row[] getLadder() {
		return this.rows;
	}
}
