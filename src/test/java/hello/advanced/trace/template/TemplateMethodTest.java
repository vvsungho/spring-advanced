package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubclassLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0() {
    /** 로직1, 로직2는 비즈니스 로직을 제외한 모든 코드가 동일하다. */
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();
    //비즈니스 로직 실행
    log.info("비즈니스 로직1 실행");
    //비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();
    //비즈니스 로직 실행
    log.info("비즈니스 로직2 실행");
    //비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  /**
   * 템플릿 메서드 패턴 적용
   */
  @Test
  void templateMethodV1() {
    AbstractTemplate template1 = new SubclassLogic1();
    template1.execute();

    AbstractTemplate template2 = new SubclassLogic1();
    template2.execute();
  }

  /**
   * 익명 클래스 패턴 (하나하나 클래스 생성이 번거롭거나 비효율이라고 판단될 때 사용)
   */
  @Test
  void templateMethodV2() {
    AbstractTemplate template1 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비즈니스 로직 1");
      }
    };

    log.info("클래스명 : {}", template1.getClass());

    AbstractTemplate template2 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비즈니스 로직 2");
      }
    };

    log.info("클래스명 : {}", template2.getClass());
  }

}
