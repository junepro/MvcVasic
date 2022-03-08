package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너") //요청할때마다 객체 새로 생성 메모리 낭비심함
    void pureContainer() {

        AppConfig appConfig = new AppConfig();
        //1. 조회 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        //참조값 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //멤버서비스 1과 2는 달라야 한다
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}