package nhb.cf.api.v4;

import org.apache.http.client.methods.RequestBuilder;

import com.nhb.messaging.http.HttpClientHelper;

import nhb.cf.api.v4.message.CloudFlareRequest;
import nhb.cf.api.v4.message.ResolvableRequest;

public interface CloudFlareApi {

	static CloudFlareApi newDefault(CloudFlareApiConfig config) {
		return new DefaultCloudFlareAPI(config);
	}

	CloudFlareFuture send(CloudFlareRequest request);
}

class DefaultCloudFlareAPI implements CloudFlareApi {

	private final HttpClientHelper httpClient;

	private final CloudFlareApiConfig config;

	DefaultCloudFlareAPI(CloudFlareApiConfig config) {
		this.config = config;
		this.httpClient = new HttpClientHelper();
		this.httpClient.setUsingMultipath(false);
	}

	@Override
	public CloudFlareFuture send(CloudFlareRequest request) {

		request.verify();

		if (request instanceof ResolvableRequest) {
			// this invoking should always call first
			((ResolvableRequest) request).resolve(this.config);
		}

		String path = request.getPath();
		String endpoint = config.getApiEndpoint() + (path.startsWith("/") ? "" : "/") + path;

		RequestBuilder reqBuilder = createRequestBuilder(request.getMethod());
		reqBuilder.setUri(endpoint);

		return CloudFlareFuture.newDefault(this.httpClient.executeAsync(reqBuilder, request.toPuObject()),
				request.getResponseType());
	}

	private RequestBuilder createRequestBuilder(String method) {
		RequestBuilder reqBuilder = RequestBuilder.create(method.toUpperCase());
		reqBuilder.addHeader("X-Auth-Email", config.getEmail());
		reqBuilder.addHeader("X-Auth-Key", config.getKey());
		return reqBuilder;
	}
}
