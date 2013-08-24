package webserver;

import static util.Constants.NEW_LINE;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.Position;

import pieces.Piece.Color;
import chess.Board;
import chess.DefaultInitialize;
import chess.Generatable;
import chess.HtmlGenerator;
import chess.HtmlGenerator2;
import chess.Initializable;
import chess.Rank;

public class RequestHandler extends Thread {
    private final static Logger log = Logger.getLogger(RequestHandler.class
            .getName());

    private static final String DEFAULT_WEBAPPS_DIR = "./webapps";

    private Socket connection;

	private FileInputStream fis;

	private Board board;

    public RequestHandler(Socket connection, Board board) {
        this.connection = connection;
        this.board = board;
    }

    public void run() {
        log.log(Level.INFO, "WebServer Thread Created!");
        InputStream is = null;
        OutputStream os = null;
        
        try {
            is = connection.getInputStream(); // 사용자 요청
            os = connection.getOutputStream(); // 사용자 응답

            // request message print
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            HttpRequest hr = new HttpRequest();
            String header = br.readLine();
            String path = header;	// get first line
            while(!"".equals(header) && !(header == null)) {
            	System.out.println(header);
            	header = br.readLine();
            }
            
            HttpRequest httpRequest = new HttpRequest();
            if (path == null) {
            	return;
            }
            
        	String requestUrl = httpRequest.parseRequestUrl(path);
        	
            if (requestUrl.contains(".js")) {
				File requestFile = new File(DEFAULT_WEBAPPS_DIR + requestUrl);
				DataOutputStream dos = new DataOutputStream(os);
				fis = new FileInputStream(requestFile);
				responseJavascriptOk(dos, requestFile.length());
        	
				int data = fis.read();
	        	while (data != -1) {
	        		os.write(data);
	        		data = fis.read();
				}			
        	} else if (requestUrl.contains(".")) {
				File requestFile = new File(DEFAULT_WEBAPPS_DIR + requestUrl);
				DataOutputStream dos = new DataOutputStream(os);
				fis = new FileInputStream(requestFile);
				
				int data = fis.read();
	        	while (data != -1) {
	        		os.write(data);
	        		data = fis.read();
	        	}
	        } else if (requestUrl.contains("move")) {
				Map<String, String> move = httpRequest.parseParameters(requestUrl);
				board.movePiece(move.get("source"), move.get("target"));
			} else if (requestUrl.contains("new")) {
				board.init();
			}

            String HtmlString = board.generateBoard(new HtmlGenerator2());
            os.write(HtmlString.getBytes());
            
            connection.close();
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
    
    private void responseHtmlOk(DataOutputStream dos, long contentsSize)    throws IOException {
        responseOk(dos, contentsSize, "text/html");
    }

    private void responseJavascriptOk(DataOutputStream dos, long contentsSize) throws IOException {
        responseOk(dos, contentsSize, "text/javascript");
    }

    private void responseOk(DataOutputStream dos, long contentsSize, String contentType)
            throws IOException {
        dos.writeBytes("HTTP/1.0 200 Document Follows " + NEW_LINE);
        dos.writeBytes("Content-Type: " + contentType + ";charset=utf-8" + NEW_LINE);
        dos.writeBytes("Content-Length: " + contentsSize + NEW_LINE);
        dos.writeBytes(NEW_LINE);
    }
}
