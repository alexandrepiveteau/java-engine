package heigvd.engine.ot.sync;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentityTransformationTest {

  @Test
  public void identityTransformationOperationsRemainSame() {
    Transformation<String> transformation = Transformation.identity();
    assertEquals("Hello", transformation.transform("Hello", "World."));
  }

}
