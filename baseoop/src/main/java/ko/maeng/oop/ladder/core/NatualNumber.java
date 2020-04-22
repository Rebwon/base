package ko.maeng.oop.ladder.core;

import java.util.Objects;

public class NatualNumber {
	private static final int INTERVAL = 1;
	private int number;

	public NatualNumber(int number) {
		if(number < INTERVAL) {
			throw new IllegalArgumentException();
		}
		this.number = number;
	}

	public static NatualNumber createFromArrayIndex(int index) {
		return new NatualNumber(index + INTERVAL);
	}

	public int getNumber() {
		return number;
	}

	public int toArrayIndex() {
		return number - INTERVAL;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NatualNumber that = (NatualNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
