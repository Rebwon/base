package ko.maeng.cleancoders.refactoring;

import java.util.ArrayList;
import java.util.List;

public class SymbolReplacer {
	protected String stringToReplace;
	protected List<String> alreadyReplaced = new ArrayList<>();
	private SymbolTranslator symbolTranslator;
	private SymbolIterator symbolIterator;

	public SymbolReplacer(String s, SymbolTranslator symbolTranslator) {
		this.stringToReplace = s;
		this.symbolTranslator = symbolTranslator;
		this.symbolIterator = new SymbolIterator(s);
	}

	public String replace() {
		for (String symbolName = symbolIterator.nextSymbol();
			 symbolName != null;
			 symbolName = symbolIterator.nextSymbol()
		) {
			replaceAllInstances(symbolName);
		}
		return stringToReplace;
	}

	private void replaceAllInstances(String symbolName) {
		if (shouldReplaceSymbol(symbolName))
			replaceSymbol(symbolName);
	}

	private void replaceSymbol(String symbolName) {
		alreadyReplaced.add(symbolName);
		symbolTranslator.translate(symbolName, this);
	}

	private boolean shouldReplaceSymbol(String symbolName) {
		return symbolTranslator.getSymbol(symbolName) != null &&
			!alreadyReplaced.contains(symbolName);
	}
}
