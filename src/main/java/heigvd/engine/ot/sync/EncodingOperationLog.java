package heigvd.engine.ot.sync;

import heigvd.engine.ot.AbstractOperationLog;

/**
 * An implementation of an {@link AbstractOperationLog} that supports
 * the process of encoding and decoding operations. Actually, it only
 * contains some primitive decoders and encoders.
 * <p>
 * A bijection should exist between the encoding space and the operation
 * space.
 *
 * @param <T>        The type of the operations that are in the log.
 * @param <Encoding> The type of the encoded data for the operations.
 *
 * @author Alexandre Piveteau (alexandre.piveteau@heig-vd.ch)
 */
public abstract class EncodingOperationLog<T, Encoding> extends AbstractOperationLog<T> {

  private OperationDecoder<T, Encoding> decoder;
  private OperationEncoder<T, Encoding> encoder;

  public EncodingOperationLog(OperationDecoder<T, Encoding> decoder,
                              OperationEncoder<T, Encoding> encoder) {
    this.decoder = decoder;
    this.encoder = encoder;
  }

  protected OperationDecoder<T, Encoding> getDecoder() {
    return decoder;
  }

  protected OperationEncoder<T, Encoding> getEncoder() {
    return encoder;
  }

}
