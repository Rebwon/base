package ko.maeng.cleancoders.refactoring;

public abstract class SymbolTranslator {
	abstract protected String getSymbol(String symbolName);

	private String symbolExpression(String symbolName) {
		return "$" + symbolName;
	}

	void translate(String symbolName, SymbolReplacer symbolReplacer) {
		symbolReplacer.stringToReplace = symbolReplacer.stringToReplace.replace(
			symbolExpression(symbolName),
			getSymbol(symbolName));
	}
}
