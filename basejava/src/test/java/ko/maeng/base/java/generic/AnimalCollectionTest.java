package ko.maeng.base.java.generic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AnimalCollectionTest {
	@Test
	public void feedingAnimalList() {
		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal());
		animals.add(new Dog());
		animals.add(new Cat());
		feedAnimalsWildCard(animals);
	}

	private void feedAnimals1(List<Animal> animals) {
		for(Animal animal : animals) {
			animal.eat();
		}
	}

	// ?는 상위타입이 Animal이라면 추가가 가능하다.
	private void feedAnimals(List<? extends Animal> animals) {
		for(Animal animal : animals) {
			animal.eat();
		}
	}

	// T는 와일드카드로써, 타입 제한을 위해 사용한다.
	private <T extends Animal> void feedAnimalsWildCard(List<T> animals) {
		for(Animal animal : animals) {
			animal.eat();
		}
	}
}