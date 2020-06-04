package ko.maeng.cleancoders.refactoring;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SymbolReplacerTest {

	@Test
	void foo() {
		MyReplacer replacer = new MyReplacer("$ss aa $bb dd ff ss");
		assertThat(replacer.replace()).isEqualTo("__ aa __ dd ff ss");
	}
}