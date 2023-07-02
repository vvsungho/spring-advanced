package hello.advanced.trace.threadlocal.test;

import hello.advanced.trace.threadlocal.code.FiledService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FieldServiceTest {

  private final FiledService filedService = new FiledService();

  @Test
  void field() {
    log.info("main start");

    Runnable userA = () -> {
      filedService.logic("userA");
    };

    Runnable userB = () -> {
      filedService.logic("userB");
    };

    // 자바 기본 서적을 봐야겠다..
    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");

    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
//    sleep(2000); // 동시성 문제 발생 안하게 테스트
    sleep(100); // 동시성 문제 발생 하게 테스트
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
