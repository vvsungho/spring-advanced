package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.StrategyLogin1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogin2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 전략을 파라미터로 전달 받는 방식
 */
@Slf4j
public class ContextV2Test {
  /**
   * 전략 패턴 적용
   */
  @Test
  void strategyV1() {
    ContextV2 contextV2 = new ContextV2();
    contextV2.execute(new StrategyLogin1());
    contextV2.execute(new StrategyLogin2());
  }

  /**
   * 전략 패턴 익명 내부 클래스
   */

  @Test
  void strategyV2() {
    ContextV2 contextV2 = new ContextV2();
    contextV2.execute(() -> log.info("비즈니스 로직 1 실행"));
    contextV2.execute(() -> log.info("비즈니스 로직 2 실행"));
  }
}
