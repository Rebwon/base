package ko.maeng.cleancoders.tdd;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MyStackTest {

	@Test
	void pop_should_return_pushed_value() {
		MyStack stack = new MyStack();
		stack.push(1);
		stack.push(2);

		assertThat(stack.pop()).isEqualTo(2);
		assertThat(stack.pop()).isEqualTo(1);
	}

	private class MyStack {
		private static final int STACK_SIZE = 100;
		private int[] value = new int[STACK_SIZE];
		private int index = 0;

		public int pop() {
			return value[--index];
		}

		public void push(int value) {
			this.value[index++] = value;
		}
	}
}
