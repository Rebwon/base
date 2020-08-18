package ko.maeng.designpattern.study.creational.abstractfactory;

public class ElfArmy implements Army {
	static final String DESCRIPTION = "This is the Elf Army!";

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
