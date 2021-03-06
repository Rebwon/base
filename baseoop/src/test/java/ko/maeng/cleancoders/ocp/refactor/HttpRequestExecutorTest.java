package ko.maeng.cleancoders.ocp.refactor;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;

class HttpRequestExecutorTest {
	private String requestURI = "https://www.daum.net/";
	private Map<String,String> params;
	private HttpRequestExecutor executor;

	@Test
	void get() throws IOException {
		ResponseModel responseModel = new GETRequestExecutor().handleRequest(requestURI, params);
		assertThat(responseModel.getResponseAsText()).startsWith("<!DOCTYPE html><html lang=\"ko\" class=\"\"><head><meta charset=\"utf-8\"/><title>Daum</title>");
	}

	@Test
	void post() throws IOException {
		ResponseModel responseModel = new POSTExecutor().handleRequest(requestURI, params);
		assertThat(responseModel.getResponseAsText()).startsWith("<!DOCTYPE html><html lang=\"ko\" class=\"\"><head><meta charset=\"utf-8\"/><title>Daum</title>");
	}
}