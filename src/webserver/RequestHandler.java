package webserver;

import static util.Constants.NEW_LINE;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler extends Thread {
    private final static Logger log = Logger.getLogger(RequestHandler.class
            .getName());

    private static final String DEFAULT_WEBAPPS_DIR = "./webapps";

    private Socket connection;

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        log.log(Level.INFO, "WebServer Thread Created!");
        InputStream is = null;
        OutputStream os = null;
        
        try {
            is = connection.getInputStream(); // 사용자 요청
            os = connection.getOutputStream(); // 사용자 응답

            // 구현 

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
