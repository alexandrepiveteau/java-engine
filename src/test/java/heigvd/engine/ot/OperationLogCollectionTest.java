package heigvd.engine.ot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationLogCollectionTest {

  private <T> OperationLog<T> provideOperationLog() {
    return new LocalOperationLog<>();
  }

  @Test
  public void testEmptyOperationLogIsReallyEmpty() {
    OperationLog<LogOperation> log = provideOperationLog();
    assertTrue(log.isEmpty());
    assertEquals(0, log.size());
  }

  @Test
  public void testEmptyOperationLogCanAppendOperations() {
    OperationLog<LogOperation> log = provideOperationLog();
    log.append(LogOperation.increment());
    log.append(LogOperation.decrement());
    log.append(LogOperation.increment());
    assertFalse(log.isEmpty());
    assertEquals(3, log.size());
  }

  @Test
  public void testOperationLogTransientCanReadOperations() {
    OperationLog<LogOperation> log = provideOperationLog();
    LogOperation o1 = LogOperation.increment();
    LogOperation o2 = LogOperation.decrement();
    LogOperation o3 = LogOperation.increment();
    log.append(o1);
    log.append(o2);
    log.append(o3);
    LogOperation c1 = log.atIndex(0);
    LogOperation c2 = log.atIndex(1);
    LogOperation c3 = log.atIndex(2);
    assertSame(o1, c1);
    assertSame(o2, c2);
    assertSame(o3, c3);
  }

  @Test
  public void testOperationLogCommittedCanReadOperations() {
    OperationLog<LogOperation> log = provideOperationLog();
    LogOperation o1 = LogOperation.increment();
    LogOperation o2 = LogOperation.decrement();
    LogOperation o3 = LogOperation.increment();
    log.append(o1);
    log.append(o2);
    log.append(o3);
    try {
      log.commit();
      LogOperation c1 = log.atIndex(0);
      LogOperation c2 = log.atIndex(1);
      LogOperation c3 = log.atIndex(2);
      assertSame(o1, c1);
      assertSame(o2, c2);
      assertSame(o3, c3);
    } catch (CommitException any) {
      fail(any);
    }
  }

  @Test
  public void testOperationLogPartiallyCommittedCanReadOperations() {
    OperationLog<LogOperation> log = provideOperationLog();
    LogOperation o1 = LogOperation.increment();
    LogOperation o2 = LogOperation.decrement();
    LogOperation o3 = LogOperation.increment();
    log.append(o1);
    log.append(o2);
    try {
      log.commit();
      log.append(o3);
      LogOperation c1 = log.atIndex(0);
      LogOperation c2 = log.atIndex(1);
      LogOperation c3 = log.atIndex(2);
      assertSame(o1, c1);
      assertSame(o2, c2);
      assertSame(o3, c3);
    } catch (CommitException any) {
      fail(any);
    }
  }

}
