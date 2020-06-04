package ko.maeng.cleancoders.refactoring;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SymbolReplacer {
	protected String stringToReplace;
	protected List<String> alreadyReplaced = new ArrayList<>();
	private final Pattern symbolPattern = Pattern.compile("\\$([a-zA-Z]\\w*)");
	private Matcher symbolMatcher;

	public SymbolReplacer(String s) {
		this.stringToReplace = s;
		this.symbolMatcher = symbolPattern.matcher(stringToReplace);
	}

	public String replace() {
		for (String symbolName = nextSymbol();
			symbolName != null;
			symbolName = nextSymbol()
		) {
			replaceAllInstances(symbolName);
		}
		return stringToReplace;
	}

	private String nextSymbol() {
		return symbolMatcher.find() ? symbolMatcher.group(1) : null;
	}

	private void replaceAllInstances(String symbolName) {
		if (shouldReplaceSymbol(symbolName))
			replaceSymbol(symbolName);
	}

	private void replaceSymbol(String symbolName) {
		alreadyReplaced.add(symbolName);
		stringToReplace = stringToReplace.replace("$" + symbolName, getSymbol(symbolName));
	}

	private boolean shouldReplaceSymbol(String symbolName) {
		return getSymbol(symbolName) != null && !alreadyReplaced.contains(symbolName);
	}

	abstract protected String getSymbol(String symbolName);
}
