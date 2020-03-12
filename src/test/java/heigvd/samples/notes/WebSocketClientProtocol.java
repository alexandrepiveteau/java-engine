package heigvd.samples.notes;

import heigvd.engine.ot.CommitException;
import heigvd.engine.ot.sync.Protocol;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * An implementation of a simple {@link Protocol} that makes use of some
 * web-sockets to send data from a client to a remote server.
 *
 * This is specifically defined for a {@link ReplicaClient}, which contains
 * some primitives to manage sessions and return synchronous answers from
 * the websocket API.
 */
public class WebSocketClientProtocol implements Protocol<JSONObject> {

  private ReplicaClient client;

  public WebSocketClientProtocol(ReplicaClient client) {
    this.client = client;
  }

  @Override
  public List<JSONObject> pushAllTransientForCommit(final int lastIndex,
                                                    final List<JSONObject> ops) throws CommitException {
    try {
      JSONObject message = new JSONObject();
      message.put("lastIndex", lastIndex);
      message.put("operations", new JSONArray(ops));

      client.send(message.toString());
      // TODO : Read the answer from the remote server.
      return null;
    } catch (Exception any) {
      // TODO : Make this exception more specific.
      throw new CommitException(any) {};
    }
  }

}
