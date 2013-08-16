package webserver;

import static util.Constants.NEW_LINE;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
		BufferedReader br = null;
		DataOutputStream dos = null;

		try {
			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			dos = new DataOutputStream(connection.getOutputStream());

			String header = br.readLine();
			
			if (header==null || header.equals("")) {
				return;
			}
			
			HttpRequest request = new HttpRequest();
			String requestUrl = request.parseRequestUrl(header);
			String requestPath = request.parseRequestPath(requestUrl);
			System.out.println("requestPath : " + requestPath);

			File file = new File(DEFAULT_WEBAPPS_DIR + requestPath);
			if (file.exists()) {
				responseHtmlOk(dos, file.length());
				FileInputStream fis = new FileInputStream(file);
				int data = fis.read();
				while (data != -1) {
					dos.write(data);
					data = fis.read();
				}
				fis.close();
			}

			connection.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	private void responseHtmlOk(DataOutputStream dos, long contentsSize)
			throws IOException {
		responseOk(dos, contentsSize, "text/html");
	}

	private void responseJavascriptOk(DataOutputStream dos, long contentsSize)
			throws IOException {
		responseOk(dos, contentsSize, "text/javascript");
	}

	private void responseOk(DataOutputStream dos, long contentsSize,
			String contentType) throws IOException {
		dos.writeBytes("HTTP/1.0 200 Document Follows " + NEW_LINE);
		dos.writeBytes("Content-Type: " + contentType + ";charset=utf-8"
				+ NEW_LINE);
		dos.writeBytes("Content-Length: " + contentsSize + NEW_LINE);
		dos.writeBytes(NEW_LINE);
	}
}
