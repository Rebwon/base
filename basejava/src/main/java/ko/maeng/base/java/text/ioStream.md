#### Java Write To File

자바로 파일에 쓰기 전 중요한 참고사항

- 존재하지 않는 파일에서 읽으려고 하면, FileNotFoundException 발생
- 존재하지 않는 파일에 쓰려고 하면, 파일이 먼저 생성되고 예외는 발생하지 않는다.
- 암시적으로 닫히지 않기 때문에, 사용된 스트림은 꼭 닫아야 한다.
- 출력 스트림에서 close() 메소드는 버퍼링 된 바이트가 스트림에 기록되도록 하는 자원을 해제하기 전에 flush()를 호출한다.

일반적인 IO Writer API 사용법

PrintWriter : 형식이 지정된 텍스트를 쓰는데 사용
FileOutputStream : 이진 데이터를 쓰는데 사용
DataOutputStream : Primitive 타입의 데이터를 쓰는데 사용
RandomAccessFile : 특정 위치에 쓰는데 사용
FileChannel : 더 큰 파일을 더 빨리 쓸 수 있게 만드는데 사용.