package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //이경우 서비스를 바꾸고싶을 경우 의존적인 관계라 dip 위반 가능성 농후
    //so dip 위반하지않기 위해선 추상화를 의존하는 게 좋음
    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;
    // - > private DiscountPolicy rateDiscountPolicy  로 필드병을 빈 이름으로 변경

    //requiredAragsConstructor 쓰면 밑에거를 자동생성
    //@Autowired 생성자가 하나이면 알아서 생성자한테 autuwired 기능 구현됨 but 2개이상일 경우 지정해줘야됨
    public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
