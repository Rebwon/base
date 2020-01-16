### public static void main(String[] args)란?

- 메인 메서드는 진입점(Entry Point)을 뜻한다. 그러므로 메인 메서드의 접근자는 항상 public이어야 한다.
- 메인 메서드는 항상 정적이어야 한다. 클래스는 메모리에 로딩된 다음에 사용이 가능하다. static이 붙은 클래스나 메서드, 변수는
컴파일 시 자동으로 로딩된다. 메인 메서드는 로딩없이 호출될 수 있어야 한다. 
- void는 리턴타입이 없다는 뜻이다. 메인 메서드는 프로그램의 알파이면서 오메가이다. 메인으로 시작해서 메인이
끝나면 그 프로그램도 끝이다. 그러므로 리턴하는 값 자체가 불 필요하다. 프로그램이 끝났는데, 마지막에 어떤 값을 리턴해봤자 아무 의미가 
없기 때문이다.
- String[] args는 프로그램 실행 시 매개변수를 보내서 실행할 수 있다는 것을 뜻한다. ex) 외부 ec2에서 실행 시 java -jar 후에 -Dspring옵션이나 -Dvmoption, Program argument등
1개를 사용할 수도 있고, 여러개를 사용할 수 있기에 배열을 사용한다.