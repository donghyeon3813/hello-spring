package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller// 컨트롤러를 선언하면 스프링이 처음 시작될 때 스프링 컨테이너에서 MemberController를 관리한다.
public class MemberController {
    private final MemberService memberService; // 여러개의 instance를 사용할 필요가없다.

    /**
     * 스프링 빈을 등록하는 방법
     * 컴포넌트 스캔과 자동 의존관계 설정 ex) @controller, @service, @Repository에 @Component가 존재하며 컴포넌트 스캔에 의해 등록된다.
     * 자바 코드로 직접 스프링 빈 등록하기
     *
     * 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다.(유일하게 하나만 등록해서 공유)
     */
    @Autowired //스프링 컨테이너에서 관리되는 인스턴스를 가져와준다. DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form) {

        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
}
