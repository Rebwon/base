package ko.maeng.cleancoders.tdd;

public class WordWrap {
	public WordWrap() {
	}

	String wrap(String s, int width) {
		if (s == null)
			return "";
		if (s.length() <= width)
			return s;
		int breakPoint = s.lastIndexOf(" ", width);
		if (breakPoint == -1)
			breakPoint = width;
		return s.substring(0, breakPoint) + "\n" + wrap(s.substring(breakPoint).trim(), width);
	}
}