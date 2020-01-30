package ko.maeng.designpattern.study.templateMethod;

public abstract class Person {
    void readyToBattle(){
        startBodyTraining();
        prepareWeapon();
        prepareArmor();
        if(isUsingPrepareOther()){
            prepareOther();
        }
    }

    final void startBodyTraining() {
        System.out.println("체력을 단련합니다.");
    }

    // 무기 손질
    abstract void prepareWeapon();

    // 갑옷 손질
    abstract void prepareArmor();

    // 후킹 용도로 사용
    boolean isUsingPrepareOther() {
        return false;
    }

    // 다른 무언가가 필요하면 사용
    void prepareOther(){};
}
