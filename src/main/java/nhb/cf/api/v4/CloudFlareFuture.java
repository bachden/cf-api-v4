package nhb.cf.api.v4;

import org.apache.http.HttpResponse;

import com.nhb.common.Loggable;
import com.nhb.common.async.RPCFuture;
import com.nhb.common.async.translator.RPCFutureTranslator;
import com.nhb.common.data.PuElement;
import com.nhb.common.data.PuObject;
import com.nhb.common.data.PuObjectRO;
import com.nhb.messaging.http.HttpAsyncFuture;
import com.nhb.messaging.http.HttpClientHelper;

import lombok.Getter;
import nhb.cf.api.v4.message.CloudFlareResponse;

public interface CloudFlareFuture extends RPCFuture<CloudFlareResponse> {

	static CloudFlareFuture newDefault(HttpAsyncFuture future, Class<? extends CloudFlareResponse> responseType) {
		return new DefaultCloudFlareFuture(future, responseType);
	}

	Class<? extends CloudFlareResponse> getResponseType();
}

class DefaultCloudFlareFuture extends RPCFutureTranslator<HttpResponse, CloudFlareResponse>
		implements CloudFlareFuture, Loggable {

	@Getter
	private final Class<? extends CloudFlareResponse> responseType;

	public DefaultCloudFlareFuture(HttpAsyncFuture future, Class<? extends CloudFlareResponse> responseType) {
		super(future);
		this.responseType = responseType;
		if (this.responseType == null) {
			throw new NullPointerException("Response type cannot be null");
		}
	}

	@Override
	protected CloudFlareResponse translate(HttpResponse sourceResult) throws Exception {
		PuElement response = HttpClientHelper.handleResponse(sourceResult);
		if (response instanceof PuObject) {
			CloudFlareResponse instance = responseType.newInstance();
			instance.fromPuObject((PuObjectRO) response);
			return instance;
		}
		throw new Exception("Response invalid");
	}
}
