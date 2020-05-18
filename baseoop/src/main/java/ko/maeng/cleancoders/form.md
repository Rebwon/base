## Form

- 아래의 경우를 제외하고는 공란을 함부로 사용하지 말아라
  - 메서드 사이
  - private 변수들과 public 변수들 사이
  - 메서드 내
    - 변수 선언과 실행 블록 사이
    - if/while 블록과 다른 코드 사이
    
### Data Structure

- class
  - private 변수들 + 이를 다루는 함수들
  - cohesive groups of variables를 조작하는 메서드
  - 구현을 hide, abstract
  - Tell이 가능

- data structure
  - public 변수들 + getter/setter
  - 개별 변수들을 조작(getter/setter)
  - 구현을 노출
  - Tell은 불가 Ask만 가능