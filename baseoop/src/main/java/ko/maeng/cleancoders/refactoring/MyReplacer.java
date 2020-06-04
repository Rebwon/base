package ko.maeng.cleancoders.refactoring;

public class MyReplacer extends SymbolReplacer {
	public MyReplacer(String s) {
		super(s);
	}

	@Override
	protected String getSymbol(String symbolName) {
		return "__";
	}
}
