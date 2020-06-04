package ko.maeng.cleancoders.refactoring;

public class MyReplacer extends SymbolTranslator {
	@Override
	protected String getSymbol(String symbolName) {
		return "__";
	}
}
