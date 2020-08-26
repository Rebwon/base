## MonoState 패턴

모든 인스턴스 간에 동일한 상태를 공유하는 것과 같은 동작을 기대합니다.

적용을 고려하는 경우
- 클래스의 모든 인스턴스에서 동일한 상태를 공유해야 합니다.
- 일반적으로 이 패턴은 Singleton이 사용될 수 있는 모든 곳에서 사용 가능합니다. 그러나, Singleton은 
투명하지 않으며, MonoState 사용은 투명합니다.
- MonoState는 Singleton에 비해 한가지 주요 이점이 있습니다. 하위 클래스는 공유 상태를 원하는 대로 꾸밀 수 있으므로 기본 클래스와 동적으로 다른 동작을
제공할 수 있습니다.

일반적 사용 사례
- 로깅 클래스
- 데이터베이스 커넥션
- 파일 관리자