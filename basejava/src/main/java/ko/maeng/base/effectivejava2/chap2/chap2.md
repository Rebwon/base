### Effective Java 2E 

#### equals를 재정의 할 때는 일반 규약을 따르라.

1. == 연산자를 사용하여 equals의 인자가 자기 자신인지 검사하라. 만일 그렇다면, true를 반환하라.
 단순히 성능 최적화를 위한 것이다. 객체 비교 오버헤드가 클 경우에 위력을 발휘한다.
 
2. instanceof 연산자를 사용하여 인자의 자료형이 정확한지 검사하라. 그렇지 않다면 false를 반환하라.
 보통 인자의 자료형은 equals가 정의된 클래스와 같아야 한다. 그 클래스가 구현하는 인터페이스 가운데 하나가
 되어야 할 때도 있다. 인터페이스의 equals 규약이 해당 인터페이스를 구현하는 여러 클래스의 모든 객체를 비교할 수 
 있도록 다듬어져 있을 때는 instanceof의 두 번째 피연산자로 해당 인터페이스를 사용해야 한다. Set이나 List,Map,Map.Entry와
 같은 컬렉션 인터페이스들이 이런 특징을 가지고 있다.
 
3. equals의 인자를 정확한 자료형으로 변환하라. 그 앞에 instanceof를 사용한 검사 코드를 두었으므로, 형 변환은 반드시 성공할 
것이다.

4. "중요" 필드 각각이 인자로 주어진 객체의 해당 필드와 일치하는지 검사한다. 모두 일치하는 경우에는 true를 반환하고, 그렇지 않은 경우에는
 false를 반환한다. 2단계에서 검사한 자료형이 인터페이스였다면 인터페이스 메서드를 통해 필드에 접근해야 할 것이다. 클래스였다면, 
 권한만 있으면 직접 접근할 수도 있을 것이다. float나 double 이외의 기본 자료형은 == 연산자로 비교하면 된다. 객체 참조 필드는 equals 메서드를 재귀적으로
  호출하여 검사한다. float필드는 Float.compare 메서드를 사용해서 비교하고, double 필드는 Double.compare 메서드를 사용해서 비교한다.