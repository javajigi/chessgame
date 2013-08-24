package webserver;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	public String parseRequestUrl(String header) {
		String requestUrl = header.substring(header.indexOf(" ")+1, header.lastIndexOf(" "));
		return requestUrl;
	}

	public String parseRequestPath(String requestUrl) {
		String requestPath = requestUrl.substring(0, requestUrl.indexOf("?"));
		return requestPath;
	}

	public Map<String, String> parseParameters(String requestUrl) {
		Map<String, String> parameters = new HashMap<String, String>();
		String parameterString = requestUrl.substring(requestUrl.indexOf("?")+1);
		if (parameterString.isEmpty()) {
			return new HashMap<String, String>();
		} else {
		String[] params = parameterString.split("&");
			for (String string : params) {
				String[] keyValue = string.split("=");
				if (keyValue.length == 2) {
					parameters.put(keyValue[0], keyValue[1]);
				}
			}
			return parameters;
		}
	}
	
}
