package ko.maeng.designpattern.study.adapter;

// 클라이언트 클래스
public class GnomeEngineerManager implements Engineer {
    private Engineer engineer;

    public GnomeEngineerManager() {
        // 엔지니어 매니저는 엔지니어가 어떤 장치를 사용하든 상관하지 않는다.
        // 엔지니어가 요구사항에 맞게 기능을 구현해주기만 하면 된다.
        engineer = new GnomeEngineer();
    }

    @Override
    public void operateService() {
        engineer.operateService();
    }
}
