## LocalDate, LocalDateTime..

자바 8 이전의 시간과 관련한 라이브러리는 Date와 Calendar였고 날짜를 포맷팅하기 위해
SimpleDateFormatter를 사용을 많이 했었다.

Date와 Calendar는 가변 클래스였으며 설계가 매우 좋지 못했다. 그래서 Joda-Time이라는 라이브러리를
개발자들이 따로 개발해서 사용하기도 했으며, 자바 8은 이 Joda-Time을 많이 참고하여 지금의 LocalDate와 LocalDateTime으로
다시 구현했다.

Duration, Period의 공통 메서드

|메서드|정적여부|설명|
|---|---|---|
|between|참|두 시간 사이의 간격을 생성함.|
|from|참|시간 단위로 간격을 생성함.|
|of|참|주어진 구성 요소에서 간격 인스턴스를 생성함.|
|parse|참|문자열을 파싱해서 간격 인스턴스를 생성함.|
|addTo|거짓|현재값의 복사본을 생성한 다음에 지정된 Temporal 객체에 추가함.|
|get|거짓|현재 간격 정보 값을 읽음|
|isNegative|거짓|간격이 음수인지 확인함|
|isZero|거짓|간격이 0인지 확인함|
|minus|거짓|현재값에서 주어진 시간을 뺀 복사본을 생성함.|
|multipliedBy|거짓|현재값에서 주어진 시간을 곱한 복사본을 생성함.|
|negated|거짓|주어진 값의 부호를 반전한 복사본을 생성함.|
|plus|거짓|현재값에서 주어진 시간을 더한 복사본을 생성함.|
|subtractFrom|거짓|지정된 Temporal 객체에서 간격을 뺌.|

LocalDate, LocalTime, LocalDateTime, Instant 등 날짜와 시간을 표현하는 모든 클래스는 서로 비슷한 메서드를 제공한다.

|메서드|정적여부|설명|
|---|---|---|
|from|참|주어진 Temporal 객체를 이용해서 클래스의 인스턴스를 생성함.|
|now|참|시스템 시계로 Temporal 객체를 생성함.|
|of|참|주어진 구성 요소에서 Temporal 객체의 인스턴스를 생성함.|
|parse|참|문자열을 파싱해서 Temporal 객체를 생성함.|
|atOffset|거짓|시간대 오프셋과 Temporal 객체를 합침.|
|atZone|거짓|시간대와 Temporal 객체를 합침.|
|format|거짓|지정된 포매터를 이용해서 Temporal 객체를 문자열로 변환함(Instant는 지원하지않음.)|
|get|거짓|Temporal 객체의 상태를 읽음.|
|minus|거짓|특정 시간을 뺀 Temporal 객체의 복사본을 생성함.|
|plus|거짓|특정 시간을 더한 Temporal 객체의 복사본을 생성함.|
|with|거짓|일부 상태를 바꾼 Temporal 객체의 복사본을 생성함.|
