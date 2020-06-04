package ko.maeng.cleancoders.refactoring;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SymbolReplacer {
	protected String stringToReplace;
	protected List<String> alreadyReplaced = new ArrayList<>();

	public SymbolReplacer(String s) {
		this.stringToReplace = s;
	}

	public String replace() {
		Pattern symbolPattern = Pattern.compile("\\$([a-zA-Z]\\w*)");
		Matcher symbolMatcher = symbolPattern.matcher(stringToReplace);
		while(symbolMatcher.find()) {
			String symbolName = symbolMatcher.group(1);
			if (getSymbol(symbolName) != null && !alreadyReplaced.contains(symbolName)) {
				alreadyReplaced.add(symbolName);
				stringToReplace = stringToReplace.replace("$" + symbolName, getSymbol(symbolName));
			}
		}
		return stringToReplace;
	}

	abstract protected String getSymbol(String symbolName);
}
