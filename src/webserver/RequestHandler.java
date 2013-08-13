package webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler extends Thread {
	private final static Logger log = Logger.getLogger(RequestHandler.class.getName());
	
	private Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	public void run() {
		log.log(Level.INFO, "WebServer Thread Created!");
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = connection.getInputStream(); // 사용자 요청
			os = connection.getOutputStream(); // 사용자 응답
			
			
			// 구현해야 함.
			
			connection.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
