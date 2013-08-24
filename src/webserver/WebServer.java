package webserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import chess.Board;

public class WebServer {
	private final static Logger log = Logger.getLogger(WebServer.class.getName());
	
    public static void main(String argv[]) throws Exception {
        // 서버소켓을 생성한다. 웹서버는 기본적으로 8080번 포트를 사용한다.
        ServerSocket listenSocket = new ServerSocket(8080);
        log.log(Level.INFO, "WebServer Socket Created");

        // 클라이언트가 연결될때까지 대기한다.
        Board board = new Board();
        Socket connection;
        while ((connection = listenSocket.accept()) != null) {
        	RequestHandler requestHandler = new RequestHandler(connection, board);
            requestHandler.start();
        }
        
    }
}
