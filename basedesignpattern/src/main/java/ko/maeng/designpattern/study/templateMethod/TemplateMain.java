package ko.maeng.designpattern.study.templateMethod;

public class TemplateMain {
    public static void main(String[] args) {
        Warrior warrior = new Warrior();
        warrior.readyToBattle();

        Archer archer = new Archer();
        archer.readyToBattle();

        Wizard wizard = new Wizard();
        wizard.readyToBattle();
    }

    public static class Warrior extends Person{
        @Override
        void prepareWeapon() {
            System.out.println("검을 닦는다.");
        }

        @Override
        void prepareArmor() {
            System.out.println("갑옷을 입는다.");
        }
    }

    public static class Archer extends Person{
        @Override
        void prepareWeapon() {
            System.out.println("활을 손질한다.");
        }

        @Override
        void prepareArmor() {
        }

        @Override
        boolean isUsingPrepareOther() {
            return true;
        }

        @Override
        void prepareOther() {
            System.out.println("화살을 준비합니다.");
        }
    }

    public static class Wizard extends Person{
        @Override
        void prepareWeapon() {
            System.out.println("지팡이를 준비합니다.");
        }

        @Override
        void prepareArmor() {
            System.out.println("로브를 입습니다.");
        }
    }
}
