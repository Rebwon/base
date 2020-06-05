package ko.maeng.cleancoders.tdd;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SortTest {

	@Test
	void sort() {
		assertThat(sort(intList())).isEqualTo(intList());
		assertThat(sort(intList(1))).isEqualTo(intList(1));
		assertThat(sort(intList(2, 1))).isEqualTo(intList(1, 2));
		assertThat(sort(intList(2, 3, 1))).isEqualTo(intList(1, 2, 3));
		assertThat(sort(intList(1, 3, 2))).isEqualTo(intList(1, 2, 3));
		assertThat(sort(intList(3, 1, 2))).isEqualTo(intList(1, 2, 3));
		assertThat(sort(intList(3, 2, 1, 2))).isEqualTo(intList(1, 2, 2, 3));
	}

	private List<Integer> sort(List<Integer> list) {
		ArrayList<Integer> sorted = new ArrayList<>();
		if(list.size() == 0)
			return list;
		else {
			List<Integer> low = new ArrayList<>();
			Integer mid = list.get(0);
			List<Integer> high = new ArrayList<>();
			for (Integer i : list.subList(1, list.size())) {
				if(i < mid)
					low.add(i);
				else
					high.add(i);
			}
			if(low != null)
				sorted.addAll(sort(low));
			sorted.add(mid);
			if(high != null)
				sorted.addAll(sort(high));
		}
		return sorted;
	}

	private List<Integer> intList(Integer... i) {
		return Arrays.asList(i);
	}
}
