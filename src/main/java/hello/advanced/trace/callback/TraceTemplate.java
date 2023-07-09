package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {
  private final LogTrace logTrace;

  public TraceTemplate(LogTrace logTrace) {
    this.logTrace = logTrace;
  }

  public <T> T execute(String message, TraceCallback<T> callback) {
    TraceStatus traceStatus = null;

    try {
      traceStatus = logTrace.begin(message);
      T result = callback.call();
      logTrace.end(traceStatus);
      return result;
    } catch (Exception e) {
      logTrace.exception(traceStatus, e);
      throw e;
    }
  }
}
