package hello.core.singleton;

//클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
//객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다
public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다
    public static SingletonService getInstance() {
        return instance;
    }

    //2개이상 생성 방지하기위해 private 설정
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출출");
   }

}
