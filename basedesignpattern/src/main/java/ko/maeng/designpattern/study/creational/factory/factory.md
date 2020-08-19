## Factory 패턴

객체를 만들기 위한 인터페이스를 정의하되, 하위 클래스가 인스턴스화할 클래스를 결정하도록함. 팩토리 메서드를 사용하면
클래스가 하위 클래스에 대한 인스턴스화를 연기할 수 있음.

Real-World-Example
- 대장장이는 무기를 제조합니다. 엘프는 엘프 무기가 필요하고 오크는 오크 무기가 필요합니다.
고객에 따라 각 유형의 대장장이가 소환됩니다.

In plain words
- 쉽게 말해, 인스턴스화를 자식 클래스에 위임하는 방법.

```java
public interface Blacksmith {
  Weapon manufactureWeapon(WeaponType weaponType);
}

public class ElfBlacksmith implements Blacksmith {
  public Weapon manufactureWeapon(WeaponType weaponType) {
    return ELFARSENAL.get(weaponType);
  }
}

public class OrcBlacksmith implements Blacksmith {
  public Weapon manufactureWeapon(WeaponType weaponType) {
    return ORCARSENAL.get(weaponType);
  }
}
```

In Use
```java
var blacksmith = new ElfBlacksmith();
blacksmith.manufactureWeapon(WeaponType.SPEAR);
blacksmith.manufactureWeapon(WeaponType.AXE);
```

실제 사용
- java.util.Calendar
- java.text.NumberFormat
- java.nio.charset.Charset
- java.util.EnumSet