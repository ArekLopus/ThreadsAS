package websockets;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class Echo {

	private Session session;

	@OnOpen
	public void connect(Session session) {
		this.session = session;
		System.out.println("Opened");
	}
	
	@OnClose
	public void close() {
		session = null;
		System.out.println("Closed");
	}
	
	@OnMessage
	public void onMessage(String msg) throws IOException {
		System.out.println("Message: "+msg);
		this.session.getAsyncRemote().sendText("(Server): "+msg);
		//this.session.getBasicRemote().sendText("Abracadabra "+msg);
	}
	
}
