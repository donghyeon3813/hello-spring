package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberREpository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration //bean 객체를 직접 관리 하기 위한 설정
public class SpringConfig {

//    private EntityManager em;
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
    //    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean //@Bean을 통해 스프링컨테이너에 등록해준다.
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); 스프링의 di를 이용하면 기존 코드를 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberREpository(dataSource);
//        return new JpaMemberRepository(em);

//    }

}
