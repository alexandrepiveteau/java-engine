package heigvd.samples.notes;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class ReplicaClient extends WebSocketClient {

  public ReplicaClient(URI server) {
    super(server);
  }

  @Override
  public void onClose(final int i, final String s, final boolean b) {
  }

  @Override
  public void onMessage(final String s) {
    System.out.println(s);
  }

  @Override
  public void onOpen(final ServerHandshake serverHandshake) {
    send("Hello world !");
  }

  @Override
  public void onError(final Exception e) {
  }

}
