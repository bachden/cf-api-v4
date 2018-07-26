package nhb.cf.api.v4.message;

import com.nhb.common.data.PuObject;

public interface CloudFlareRequest extends CloudFlareMessage {

	String getPath();

	String getMethod();

	Class<? extends CloudFlareResponse> getResponseType();

	PuObject toPuObject();
}
