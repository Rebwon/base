package ko.maeng.designpattern.study.adapter;

// 어댑터 클래스(110V를 220V로 변환해주는 변환기)
public class GnomeEngineer implements Engineer {
    private GoblinGlider glider;

    public GnomeEngineer(){
        // 요구사항이 변경될 경우 다른 Glider클래스로 바꾸어주면 된다.
        glider = new GoblinGlider();
    }

    @Override
    public void operateService() {
        glider.attachGlider();
        glider.gainSpeed();
        glider.takeOff();
    }
}
