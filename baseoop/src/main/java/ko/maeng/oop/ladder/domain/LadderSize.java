package ko.maeng.oop.ladder.domain;

import ko.maeng.oop.ladder.core.NatualNumber;

public class LadderSize {
	private NatualNumber height;
	private NatualNumber nthOfPerson;

	private LadderSize(NatualNumber height, NatualNumber nthOfPerson) {
		this.height = height;
		this.nthOfPerson = nthOfPerson;
	}

	static LadderSize create(int height, int nthOfPerson) {
		return new LadderSize(new NatualNumber(height), new NatualNumber(nthOfPerson));
	}

	NatualNumber getPositionOfPerson(NatualNumber currentTotalPosition) {
		int remainder = currentTotalPosition.getNumber() % nthOfPerson.getNumber();
		if(remainder == 0) {
			return nthOfPerson;
		}
		return new NatualNumber(remainder);
	}

	NatualNumber getPositionOfHeight(NatualNumber currentTotalPosition) {
		double ceilDividende = Math.ceil(currentTotalPosition.getNumber() / (double)nthOfPerson.getNumber());
		return new NatualNumber(new Double(ceilDividende).intValue());
	}

	public Position getPosition(NatualNumber currentTotalPosition) {
		return Position.create(getPositionOfHeight(currentTotalPosition), getPositionOfPerson(currentTotalPosition));
	}

	NatualNumber getHeight() {
		return height;
	}

	NatualNumber getNthOfPerson() {
		return nthOfPerson;
	}

	int getCountOfLine(double ratio) {
		NatualNumber totalPositions = getTotalPosition();
		return new Double(totalPositions.getNumber() * ratio).intValue();
	}

	NatualNumber getTotalPosition() {
		return height.multiply(nthOfPerson);
	}
}
