package ko.maeng.oop.ladder.domain;

import java.util.Objects;

import ko.maeng.oop.ladder.core.NatualNumber;

class Position {
	private NatualNumber height;
	private NatualNumber nthOfPerson;

	private Position(NatualNumber height, NatualNumber nthOfPerson) {
		this.height = height;
		this.nthOfPerson = nthOfPerson;
	}

	static Position create(int height, int nthOfPerson) {
		return create(new NatualNumber(height), new NatualNumber(nthOfPerson));
	}

	static Position create(NatualNumber height, NatualNumber nthOfPerson) {
		return new Position(height, nthOfPerson);
	}

	public static Position createFromArrayIndex(int height, int nthOfPerson) {
		return new Position(NatualNumber.createFromArrayIndex(height),
			NatualNumber.createFromArrayIndex(nthOfPerson));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return Objects.equals(height, position.height) &&
			Objects.equals(nthOfPerson, position.nthOfPerson);
	}

	@Override
	public int hashCode() {
		return Objects.hash(height, nthOfPerson);
	}

	@Override
	public String toString() {
		return "Position{" +
			"height=" + height +
			", nthOfPerson=" + nthOfPerson +
			'}';
	}
}
