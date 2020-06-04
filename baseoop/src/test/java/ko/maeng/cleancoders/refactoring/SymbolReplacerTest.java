package ko.maeng.cleancoders.refactoring;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SymbolReplacerTest {

	@Test
	void foo() {
		SymbolReplacer replacer = new SymbolReplacer("$ss aa $bb dd ff ss", new MyReplacer());
		assertThat(replacer.replace()).isEqualTo("__ aa __ dd ff ss");
	}
}