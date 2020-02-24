package heigvd.engine.ot.sync;

import heigvd.engine.ot.LogOperation;
import heigvd.engine.ot.LogOperationReducer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleSiteOperationLogTest {

  private PrimaryOperationLog<LogOperation, EncodedOperation> primary() {
    return new PrimaryOperationLog<>(
        new LogOperationDecoder(),
        new LogOperationEncoder(),
        Transformation.identity()
    );
  }

  private ReplicaOperationLog<LogOperation, EncodedOperation> replica(
      PrimaryOperationLog<LogOperation, EncodedOperation> primary
  ) {
    return new ReplicaOperationLog<>(
        primary.getDecoder(),
        primary.getEncoder(),
        primary.createProtocol()
    );
  }

  @Test
  public void testTwoSitesAppendOperationsWithoutCommitWorksLocally() {
    PrimaryOperationLog<LogOperation, EncodedOperation> primary = primary();
    ReplicaOperationLog<LogOperation, EncodedOperation> replica1 = replica(
        primary
    );
    ReplicaOperationLog<LogOperation, EncodedOperation> replica2 = replica(
        primary
    );

    replica1.append(LogOperation.increment());
    replica1.append(LogOperation.increment());
    replica1.append(LogOperation.increment());
    replica1.append(LogOperation.decrement());

    replica2.append(LogOperation.decrement());
    replica2.append(LogOperation.decrement());

    assertEquals(2, LogOperationReducer.reduce(replica1));
    assertEquals(-2, LogOperationReducer.reduce(replica2));
  }

}
