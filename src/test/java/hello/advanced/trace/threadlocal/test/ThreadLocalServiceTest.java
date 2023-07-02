package hello.advanced.trace.threadlocal.test;

import hello.advanced.trace.threadlocal.code.FiledService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

  private final ThreadLocalService threadLocalService = new ThreadLocalService();

  @Test
  void field() {
    log.info("main start");

    Runnable userA = () -> {
      threadLocalService.logic("userA");
    };

    Runnable userB = () -> {
      threadLocalService.logic("userB");
    };

    // 자바 기본 서적을 봐야겠다..
    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");

    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
//    sleep(2000);
    sleep(100); // 동시성 문제 발생 하게 테스트 -> ThreadLocal에선 발생하지 않음
    threadB.start();
    sleep(3000); // 메인 쓰레드 종료 대기, sleep을 하지 않으면 main thread가 종료되어 로그가 찍히지 않음

    log.info("main exit");
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
