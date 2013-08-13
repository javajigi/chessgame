package webserver;

import java.util.Map;

import junit.framework.TestCase;

public class HttpRequestTest extends TestCase {
	private HttpRequest request;
	
	@Override
	protected void setUp() throws Exception {
		request = new HttpRequest();
	}
	
	public void testRequestUrl() throws Exception {
		String header = "GET /move?source=a1&target=a2 HTTP/1.1";
		String requestUrl = request.parseRequestUrl(header);
		assertEquals("/move?source=a1&target=a2", requestUrl);
	}
	
	public void testRequestPath() throws Exception {
		String requestUrl = "/move?source=a1&target=a2";
		String requestPath = request.parseRequestPath(requestUrl);
		assertEquals("/move", requestPath);
	}
	
	public void testGetParameters() throws Exception {
		String requestUrl = "/move?source=a1&target=a2";
		Map<String, String> parameters = request.parseParameters(requestUrl);
		assertEquals("a1", parameters.get("source"));
		assertEquals("a2", parameters.get("target"));
	}
	
	public void testGetParametersException1() throws Exception {
		String requestUrl = "/move?";
		Map<String, String> parameters = request.parseParameters(requestUrl);
		assertEquals(0, parameters.size());
	}
	
	public void testGetParametersException2() throws Exception {
		String requestUrl = "/move?source=&target=";
		Map<String, String> parameters = request.parseParameters(requestUrl);
		assertEquals(0, parameters.size());
	}
	
	public void testInitialize() throws Exception {
		String header = "GET /move?source=a1&target=a2 HTTP/1.1";
		request = new HttpRequest(header);
		assertEquals("/move", request.getRequestPath());
		assertEquals("a1", request.getParameter("source"));
		assertEquals("a2", request.getParameter("target"));
	}
}
