package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import chess.Board;
import chess.HtmlGenerator2;

public class RequestHandler extends Thread {
	private final static Logger log = Logger.getLogger(RequestHandler.class
			.getName());

	private static final String DEFAULT_WEBAPPS_DIR = "./webapps";

	private Socket connection;

	private Board board;

	public RequestHandler(Socket connection, Board board) {
		this.connection = connection;
		this.board = board;
	}

	public void run() {
		log.log(Level.INFO, "WebServer Thread Created!");
		BufferedReader br = null;
		DataOutputStream dos = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String header = br.readLine();
			if (header == null) {
				return;
			}
			System.out.println(header);
			HttpRequest request = new HttpRequest(header);
			String requestPath = request.getRequestPath();

			dos = new DataOutputStream(connection.getOutputStream());
			if (requestPath.startsWith("/new")) {
				board.newGame();
				processChess(dos);
			} else if (requestPath.startsWith("/move")) {
				board.movePiece(request.getParameter("source"), request.getParameter("target"));
				processChess(dos);
			} else if (requestPath.equals("/")){
				processChess(dos);
			} else {
				processFile(dos, requestPath);
			}

			connection.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	private void processChess(DataOutputStream dos) throws IOException {
		String html = board.generateBoard(new HtmlGenerator2());
		byte[] bytes = html.getBytes();
		responseHtmlOk(dos, bytes.length);
		dos.writeBytes("\r\n");
		dos.write(bytes);
	}

	private void processFile(DataOutputStream dos, String requestPath)
			throws FileNotFoundException, IOException {
		File file = new File(DEFAULT_WEBAPPS_DIR + requestPath);
		// 요청한 파일이 존재하는가?
		if (file.exists()) {
			int numOfBytes = (int) file.length();
			FileInputStream inFile = new FileInputStream(file);
			byte[] fileInBytes = new byte[numOfBytes];
			inFile.read(fileInBytes);

			responseJavascriptOk(dos, numOfBytes);
			dos.writeBytes("\r\n");
			dos.write(fileInBytes, 0, numOfBytes);
			dos.writeBytes("\r\n");
			dos.flush();
			inFile.close();
		}
	}

	private void responseHtmlOk(DataOutputStream dos, int numOfBytes)
			throws IOException {
		dos.writeBytes("HTTP/1.0 200 Document Follows \r\n");
		dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
		dos.writeBytes("Content-Length: " + numOfBytes + "\r\n");
	}

	private void responseJavascriptOk(DataOutputStream dos, int numOfBytes)
			throws IOException {
		dos.writeBytes("HTTP/1.0 200 Document Follows \r\n");
		dos.writeBytes("Content-Type: text/javascript;charset=utf-8\r\n");
		dos.writeBytes("Content-Length: " + numOfBytes + "\r\n");
	}
}
