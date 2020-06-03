package ko.maeng.cleancoders.ocp.refactor;

public class ResponseModel {
	private String requestURL;
	private String responseText;

	public ResponseModel(String requestURL, String responseBody) {
		this.requestURL = requestURL;
		this.responseText = responseBody;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public String getResponseAsText() {
		return responseText;
	}
}
