package ko.maeng.designpattern.study.creational.abstractfactory;

public interface KingdomFactory {
	Castle createCastle();
	King createKing();
	Army createArmy();
}
