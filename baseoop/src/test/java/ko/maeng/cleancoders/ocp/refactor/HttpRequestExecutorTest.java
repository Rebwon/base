package ko.maeng.cleancoders.ocp.refactor;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HttpRequestExecutorTest {
	private String requestURI = "https://www.daum.net/";
	private Map<String,String> params;
	private Boolean isGet = true;
	private HttpRequestExecutor executor;

	@BeforeEach
	void setUp() {
		executor = new HttpRequestExecutor();
	}

	@Test
	void get() throws IOException {
		ResponseModel responseModel = executor.handleRequest(isGet, requestURI, params);
		assertThat(responseModel.getResponseAsText()).startsWith("<!DOCTYPE html><html lang=\"ko\" class=\"\"><head><meta charset=\"utf-8\"/><title>Daum</title>");
	}

	@Test
	void post() throws IOException {
		isGet = false;
		ResponseModel responseModel = executor.handleRequest(isGet, requestURI, params);
		assertThat(responseModel.getResponseAsText()).startsWith("<!DOCTYPE html><html lang=\"ko\" class=\"\"><head><meta charset=\"utf-8\"/><title>Daum</title>");
	}
}