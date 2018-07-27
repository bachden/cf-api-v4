package nhb.cf.api.v4.message;

import java.util.List;

import com.nhb.common.data.PuElement;
import com.nhb.common.data.PuObject;
import com.nhb.common.data.PuObjectRO;

public interface CloudFlareResponse extends CloudFlareMessage {

	void fromPuObject(PuObjectRO json);

	boolean isSuccessful();

	List<PuObject> getErrors();

	List<PuObject> getMessages();

	PuElement getResult();

	@SuppressWarnings("unchecked")
	default <T extends CloudFlareResponse> T cast() {
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	default <T extends CloudFlareResponse> T cast(Class<T> clazz) {
		return (T) this;
	}
}
