### Singleton Pattern

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
```

위와 같이 싱글톤을 만들 수 있다. 객체가 없다면 새로운 싱글턴 객체를 생성하고,
있다면 객체를 반환해주면 된다. 하지만 위와 같이 작성하였을 경우 멀티 스레드 환경에서 문제가 발생한다.

멀티 스레드 환경까지 고려하여 여러가지 해법을 작성해보았다.

#### synchronized 사용.

```java
public class Singleton {
    private static Singleton instance;

    private Singleton(){}

    public static synchronized Singleton getInstance(){
        if( instance == null ){
            instance = new Singleton();
        }
        return instance;
    }
}
```

#### Eager initialization 사용.

```java
public class Singleton {
    private static Singleton instance = new Singleton();
    
    private Singleton(){}
        
    public static Singleton getInstance(){
        return instance;
    }   
}
```

#### Initialization-on-demand holder idiom

```java
public class Singleton {
     private Singleton(){}

     private static class SingletonHolder {
             private static final Singleton INSTANCE = new Singleton();
     }

     public static Singleton getInstance() {
             return SingletonHolder.INSTANCE;
     }
}
```