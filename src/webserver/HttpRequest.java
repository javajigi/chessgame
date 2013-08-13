package webserver;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private String requestPath;
	private Map<String, String> parameters;

	public HttpRequest() {
	}
	
	public HttpRequest(String header) {
		String requestUrl = parseRequestUrl(header);
		this.requestPath = parseRequestPath(requestUrl);
		this.parameters = parseParameters(requestUrl);
	}
	
	public String getRequestPath() {
		return requestPath;
	}
	
	public String getParameter(String name) {
		return parameters.get(name);
	}
	
	String parseRequestUrl(String header) {
		String[] values = header.split(" ");
		return values[1];
	}

	String parseRequestPath(String requestUrl) {
		int index = requestUrl.indexOf("?");
		if (index == -1) {
			return requestUrl;
		}
		return requestUrl.substring(0, index);
	}

	Map<String, String> parseParameters(String requestUrl) {
		int index = requestUrl.indexOf("?");
		if (index == -1) {
			return new HashMap<String, String>();
		}
		
		String value = requestUrl.substring(index + 1);
		if (!value.contains("&")) {
			return new HashMap<String, String>();
		}
		
		Map<String, String> parameters = new HashMap<String, String>();
		String[] keyValues = value.split("&");

		for (String keyValue : keyValues) {
			String[] splitedKeyValue = keyValue.split("=");
			if (splitedKeyValue.length == 2) {
				parameters.put(splitedKeyValue[0], splitedKeyValue[1]);
			}
		}
		
		return parameters;
	}
	
}
