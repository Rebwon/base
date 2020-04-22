package ko.maeng.oop.ladder.core;

import java.util.Objects;

public class NatualNumber {
	private int number;

	public NatualNumber(int number) {
		if(number < 1) {
			throw new IllegalArgumentException();
		}
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public int toArrayIndex() {
		return number - 1;
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
