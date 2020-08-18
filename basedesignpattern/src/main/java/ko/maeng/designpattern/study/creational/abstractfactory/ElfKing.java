package ko.maeng.designpattern.study.creational.abstractfactory;

public class ElfKing implements King {
	static final String DESCRIPTION = "This is the Elf king!";

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
