package ko.maeng.base.java.annotations.di;

public class Component {

  @Autowired
  private Service1 service1;
  @Autowired
  private Service2 service2;

  public Service1 getService1() {
    return service1;
  }

  public Service2 getService2() {
    return service2;
  }
}
