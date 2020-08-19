package ko.maeng.designpattern.study.creational.factorykit;

public class FactoryKitMain {
	public static void main(String[] args) {
		var factory = WeaponFactory.factory(builder -> {
			builder.add(WeaponType.SWORD, Sword::new);
			builder.add(WeaponType.AXE, Axe::new);
			builder.add(WeaponType.SPEAR, Spear::new);
			builder.add(WeaponType.BOW, Bow::new);
		});
		var axe = factory.create(WeaponType.AXE);
		System.out.println(axe.toString());
	}
}
