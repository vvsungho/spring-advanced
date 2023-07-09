package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogin1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogin2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
  @Test
  void strategy() {
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
   * 전략 패턴 사용 == 스프링 의존성 주입
   */
  @Test
  void strategyV1() {
    StrategyLogin1 strategyLogin1 = new StrategyLogin1();
    ContextV1 context1 = new ContextV1(strategyLogin1);
    context1.execute();

    StrategyLogin2 strategyLogin2 = new StrategyLogin2();
    ContextV1 context2 = new ContextV1(strategyLogin2);
    context2.execute();
  }

  @Test
  void strategyV2() {
    Strategy strategy1 = new Strategy() {
      @Override
      public void call() {
        log.info("비즈니스 로직 1 실행");
      }
    };
    ContextV1 contextV1 = new ContextV1(strategy1);
    contextV1.execute();

    Strategy strategy2 = new Strategy() {
      @Override
      public void call() {
        log.info("비즈니스 로직 2 실행");
      }
    };
    ContextV1 contextV2 = new ContextV1(strategy2);
    contextV2.execute();
  }

  /** V2 코드 간결 버전 */
  @Test
  void strategyV3() {
    ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
    contextV1.execute();

    ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직 2 실행"));
    contextV2.execute();
  }
}
