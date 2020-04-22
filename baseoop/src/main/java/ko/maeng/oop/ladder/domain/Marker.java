package ko.maeng.oop.ladder.domain;

import ko.maeng.oop.ladder.core.NatualNumber;

public class Marker extends NatualNumber {
	Marker(int number) {
		super(number);
	}

	Marker moveRight() {
		return new Marker(getNumber() + 1);
	}

	Marker moveLeft() {
		return new Marker(getNumber() - 1);
	}
}
