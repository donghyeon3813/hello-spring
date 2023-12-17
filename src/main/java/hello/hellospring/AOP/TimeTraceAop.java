package hello.hellospring.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //Spring Config로 많이 관리한다.
public class TimeTraceAop {
    /*
    * 회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
    * 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
    * 핵심 관심 사항을 깔끔하게 유지할 수 있다.
    * 변경에 필요하면 이 로직에만 변경하면 된다.
    * 원하는 적용 대상을 선택할 수 있다.
    * */
    @Around("execution(* hello.hellospring.service..*(..))")//특정 폴더 하위에 적용
    public Object execut(ProceedingJoinPoint joinPoint) throws  Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }


    }
}
