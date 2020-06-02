package ko.maeng.cleancoders.tdd;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordwrapTest {

	private WordWrap wordWrap;

	@BeforeEach
	void setUp() {
		wordWrap = new WordWrap();
	}

	@Test
	void wrap() {
		assertWraps(null, 1, "");
		assertWraps("", 1, "");
		assertWraps("x", 1, "x");
		assertWraps("xx", 1, "x\nx");
		assertWraps("xxx", 1, "x\nx\nx");
		assertWraps("x x", 1, "x\nx");
		assertWraps("x xx", 3, "x\nxx");
		assertWraps("four score and seven years ago our fathers brought forth upon this continent", 7,
			"four\nscore\nand\nseven\nyears\nago our\nfathers\nbrought\nforth\nupon\nthis\ncontine\nnt");
	}

	private void assertWraps(String s, int width, String value) {
		assertThat(wordWrap.wrap(s, width)).isEqualTo(value);
	}
}
