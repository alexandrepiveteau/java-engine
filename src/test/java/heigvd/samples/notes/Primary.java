package heigvd.samples.notes;

import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Primary {

  public static void main(String[] args) {
    InetSocketAddress address = new InetSocketAddress("localhost", 8000);
    WebSocketServer server = new PrimaryServer(address);
    server.setTcpNoDelay(true);
    System.out.println("Running server on " + address);
    server.run();
  }

}
