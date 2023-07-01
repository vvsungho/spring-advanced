package hello.advanced.trace;

import java.util.UUID;

public class TraceId {

  // trace id
  private String id;

  // trace depth
  private int level;

  public TraceId() {
    this.id = createId();
    this.level = 0;
  }

  private TraceId(String id, int level) {
    this.id = id;
    this.level = level;
  }

  private String createId() {
    // uuid 길어짐 방지, 8자리만 사용
    return UUID.randomUUID().toString().substring(0, 8);
  }

  public TraceId createNextId() {
    return new TraceId(id , level + 1);
  }

  public TraceId createPreviousId() {
    return new TraceId(id, level - 1);
  }

  public boolean isFirstLevel() {
    return level == 0;
  }

  public String getId() {
    return id;
  }

  public int getLevel() {
    return level;
  }
}
