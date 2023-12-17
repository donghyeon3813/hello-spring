package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // memberRepository를 외부에서 주입한다. -> DI
        this.memberRepository = memberRepository;
    }



    /**
     * 
     * 회원 가입
     */
    public Long join(Member member) {

        long start = System.currentTimeMillis();
        try {
            //같은 이름이 있는 중복 회원X
            validateDuplicateMember(member);// 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {// 모든 메서드에 매번 시간을 측정하는 로직을 추가하기 힘들다.
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findByMembers(){
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
