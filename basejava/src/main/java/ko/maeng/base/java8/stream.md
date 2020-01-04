### StreamAPI 축약형 정리

collection.stream().forEach() 
  → collection.forEach()
  
collection.stream().toArray() 
  → collection.toArray()

Arrays.asList().stream() 
  → Arrays.stream() or Stream.of()

Collections.emptyList().stream() 
  → Stream.empty()

stream.filter().findFirst().isPresent() 
  → stream.anyMatch()

stream.collect(counting()) 
  → stream.count()

stream.collect(maxBy()) 
  → stream.max()

stream.collect(mapping()) 
  → stream.map().collect()

stream.collect(reducing()) 
  → stream.reduce()

stream.collect(summingInt()) 
  → stream.mapToInt().sum()

stream.map(x -> {...; return x;}) 
  → stream.peek(x -> ...)

!stream.anyMatch() 
  → stream.noneMatch()

!stream.anyMatch(x -> !(...)) 
  → stream.allMatch()

stream.map().anyMatch(Boolean::booleanValue) 
  → stream.anyMatch()

IntStream.range(expr1, expr2).mapToObj(x -> array[x]) 
  → Arrays.stream(array, expr1, expr2)

Collection.nCopies(count, ...) 
  → Stream.generate().limit(count)

stream.sorted(comparator).findFirst() 
  → Stream.min(comparator)
  
  
- 동기화(Synchronized)의 차이

// non synchronized  
Collections.synchronizedList(...).stream().forEach()


// synchronized
Collections.synchronizedList(...).forEach()


- collect를 생략하고 바로 max 메서드를 호출하는 경우  

collect(Collectors.maxBy())  // Optional

Stream.max() // NullPointerException 발생 가능