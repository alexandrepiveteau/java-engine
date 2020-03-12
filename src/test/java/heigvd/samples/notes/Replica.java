package heigvd.samples.notes;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class Replica {

  public static void main(String[] args) throws URISyntaxException {
    WebSocketClient client = new ReplicaClient(new URI("ws://localhost:8000"));
    client.setTcpNoDelay(true);
    client.connect();
  }

}
