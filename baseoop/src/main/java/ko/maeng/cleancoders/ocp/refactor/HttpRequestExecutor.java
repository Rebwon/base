package ko.maeng.cleancoders.ocp.refactor;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public abstract class HttpRequestExecutor {
	public static final int DEFAULT_CONNECT_TIMEOUT_SEC = 10000;
	public static final int DEFAULT_SOCKET_TIMEOUT_SEC = 30000;
	private static final String inputEncoding = "UTF-8";
	protected static final String outputEncoding = "UTF-8";
	protected HttpURLConnection urlConnection;

	public ResponseModel handleRequest(String requestURI, Map<String, String> params) throws IOException {
		String paramsString = getParamsString(params);

		URL url = createUrl(requestURI, paramsString);
		setDefaultConnectionSettings(url);

		setAdditionalConnectionSettings();
		additionalWorkWith(paramsString);
		String responseBody = getResponseBody();

		urlConnection.disconnect();

		return new ResponseModel(requestURI, responseBody);
	}

	private String getResponseBody() throws IOException {
		InputStream inputStream = urlConnection.getInputStream();
		return convertInputStreamToString(inputStream);
	}

	protected abstract void additionalWorkWith(String paramsString) throws IOException;

	protected abstract void setAdditionalConnectionSettings();

	private void setDefaultConnectionSettings(URL url) throws IOException {
		urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setReadTimeout(DEFAULT_SOCKET_TIMEOUT_SEC);
		urlConnection.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT_SEC);
	}

	protected abstract URL createUrl(String requestURI,
		String paramsString) throws MalformedURLException;

	private String getParamsString(Map<String, String> params) {
		String paramsString = "";
		if (params != null) {
			Set<String> keySet = params.keySet();
			paramsString += "?";
			for (String key : keySet) {
				paramsString += key + "=" + params.get(key) + "&";
			}
		}

		return paramsString;
	}

	private String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputEncoding));
		String line;
		StringBuffer result = new StringBuffer();

		while ((line = bufferedReader.readLine()) != null)
			result.append(line);

		close(bufferedReader);
		close(inputStream);

		return result.toString();
	}

	private void close(Closeable closeable) throws IOException {
		if (closeable != null) {
			closeable.close();
		}
	}
}
