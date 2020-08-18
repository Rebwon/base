package ko.maeng.designpattern.study.creational.abstractfactory;

public class ElfCastle implements Castle {
	static final String DESCRIPTION = "This is the Elf Castle!";

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
