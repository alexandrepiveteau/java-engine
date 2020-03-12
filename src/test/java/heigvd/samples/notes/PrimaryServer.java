package heigvd.samples.notes;

import heigvd.engine.ot.CommitException;
import heigvd.engine.ot.sync.PrimaryOperationLog;
import heigvd.engine.ot.sync.Protocol;
import heigvd.samples.notes.op.NoteOperation;
import heigvd.samples.notes.op.NoteOperationDecoder;
import heigvd.samples.notes.op.NoteOperationEncoder;
import heigvd.samples.notes.op.NoteTransformation;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class PrimaryServer extends WebSocketServer {

  private PrimaryOperationLog<NoteOperation, JSONObject> log =
      new PrimaryOperationLog<>(
          new NoteOperationDecoder(),
          new NoteOperationEncoder(),
          new NoteTransformation()
      );

  private Protocol<JSONObject> protocol = log.createProtocol();

  public PrimaryServer(InetSocketAddress address) {
    super(address);
  }

  @Override
  public void onClose(final WebSocket webSocket, final int i, final String s, final boolean b) {
  }

  @Override
  public void onError(final WebSocket webSocket, final Exception e) {
  }

  @Override
  public void onMessage(final WebSocket webSocket, final String message) {

    JSONObject object = new JSONObject(message);
    int lastIndex = object.getInt("lastIndex");
    JSONArray ops = object.getJSONArray("operations");
    List<JSONObject> operations = new ArrayList<>();
    for (int i = 0; i < object.length(); i++) {
      operations.add(ops.getJSONObject(i));
    }

    try {
      List<JSONObject> transformed = protocol.pushAllTransientForCommit(
          lastIndex,
          operations
      );
    } catch (CommitException any) {
      // TODO : Send the error back to the client.
    }

    System.out.println("Received message :");
    webSocket.send("Thanks for the heads up !");
  }

  @Override
  public void onOpen(final WebSocket webSocket, final ClientHandshake clientHandshake) {
  }

  @Override
  public void onStart() {
  }

}
