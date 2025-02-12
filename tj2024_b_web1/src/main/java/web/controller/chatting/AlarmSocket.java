package web.controller.chatting;

import java.util.List;
import java.util.Vector;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/alarmsocket")
public class AlarmSocket {
	private static List<Session>connList = new Vector<>(); // 접속한 클라이언트 소켓 정보(session)들을 리스트에 저장하기위해 리스트 생성(선언)
	
	@OnOpen // 클라이언트가 서버에 성공적으로 (자동) 성공을 했을 때
	public void onOpen(Session session) {
		connList.add(session); // 리스트에 연결/연동 된 클라이언트 소켓의 session (정보)를 리스트에 저장한다.
	}
	
	@OnClose // 클라리언트와 서버가 연결이 닫혔을 때 함수 (자동) 실행
	public void onClose(Session session) { 
		connList.remove(session); // 연결이 닫혔을 때 리시트에서 닫힌 클라이언트 소켓의 세션(정보)를 제거한다.
	}
	
	@OnMessage // 클라이언트가 보낸 메세지를 서버가 정상적으로 받았을 때. (자동) 실행
	public void onMessage(Session session , String message) {
		// 현재 접속된 모든 클라이언트 소켓들에게 받은 메세지를 보낸다.
		for(int i = 0; i <= connList.size() -1; i++) {
			Session client = connList.get(i);
			try {
				client.getBasicRemote().sendText(message);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}// c end
