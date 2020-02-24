package heigvd.engine.ot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OperationLogCommitTest {

  private <T> OperationLog<T> provideOperationLog() {
    return new LocalOperationLog<>();
  }

  @Test
  public void testEmptyOperationLogHasNoCommitted() {
    OperationLog<LogOperation> log = provideOperationLog();
    assertEquals(-1, log.commitIndex());
    assertEquals(0, log.sizeCommitted());
    assertEquals(0, log.sizeTransient());
  }

  @Test
  public void testOperationLogWithSomeOperationsHasTransient() {
    OperationLog<LogOperation> log = provideOperationLog();
    log.append(LogOperation.increment());
    log.append(LogOperation.decrement());
    log.append(LogOperation.increment());
    assertEquals(-1, log.commitIndex());
    assertEquals(0, log.sizeCommitted());
    assertEquals(3, log.sizeTransient());
  }

  @Test
  public void testEmptyOperationLogCanCommit() {
    OperationLog<LogOperation> log = provideOperationLog();
    try {
      log.commit();
      assertEquals(-1, log.commitIndex());
      assertEquals(0, log.sizeCommitted());
      assertEquals(0, log.sizeTransient());
    } catch (CommitException any) {
      fail(any);
    }
  }

  @Test
  public void testOperationLogWithSomeOperationsCanCommitAll() {
    OperationLog<LogOperation> log = provideOperationLog();
    log.append(LogOperation.increment());
    log.append(LogOperation.decrement());
    try {
      log.commit();
      assertEquals(1, log.commitIndex());
      assertEquals(2, log.sizeCommitted());
      assertEquals(0, log.sizeTransient());
    } catch (CommitException any) {
      fail(any);
    }
  }

  @Test
  public void testOperationLogWithSomeOperationsCanAppendPostCommit() {
    OperationLog<LogOperation> log = provideOperationLog();
    try {
      log.append(LogOperation.decrement());
      log.commit();
      log.append(LogOperation.increment());
      log.append(LogOperation.increment());
      assertEquals(0, log.commitIndex());
      assertEquals(3, log.size());
      assertEquals(1, log.sizeCommitted());
      assertEquals(2, log.sizeTransient());
    } catch (CommitException any) {
      fail(any);
    }
  }

}
