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
		String header = "GET index.html HTTP/1.1";
		String requestUrl = request.parseRequestUrl(header);
		assertEquals("index.html", requestUrl);
	}
	
	public void testRequestPath() throws Exception {
		String requestUrl = "index.html?source=a1&target=a2";
		String requestPath = request.parseRequestPath(requestUrl);
		assertEquals("index.html", requestPath);
	}
	
	public void testGetParameters() throws Exception {
		String requestUrl = "index.html?source=a1&target=a2";
		Map<String, String> parameters = request.parseParameters(requestUrl);
		assertEquals("a1", parameters.get("source"));
		assertEquals("a2", parameters.get("target"));
	}
	
	public void testGetParametersException1() throws Exception {
		String requestUrl = "index.html?";
		Map<String, String> parameters = request.parseParameters(requestUrl);
		assertEquals(0, parameters.size());
	}
	
	public void testGetParametersException2() throws Exception {
		String requestUrl = "index.html?source=&target=";
		Map<String, String> parameters = request.parseParameters(requestUrl);
		assertEquals(0, parameters.size());
	}
}
