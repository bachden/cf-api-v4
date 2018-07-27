package nhb.cf.api.v4.message;

import com.nhb.common.data.PuObject;

public interface CloudFlareRequest extends CloudFlareMessage {

	String getPath();

	String getMethod();

	Class<? extends CloudFlareResponse> getResponseType();

	default void verify() {
		// request must implement and throw exceptions on invalid
	}

	PuObject toPuObject();
}
