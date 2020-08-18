package ko.maeng.designpattern.study.creational.abstractfactory;

public class FactoryMain {
	public static void main(String[] args) {
		var factory = new ElfKingdomFactory();
		var castle = factory.createCastle();
		var king = factory.createKing();
		var army = factory.createArmy();

		System.out.println(castle.getDescription());
		System.out.println(king.getDescription());
		System.out.println(army.getDescription());
	}
}
