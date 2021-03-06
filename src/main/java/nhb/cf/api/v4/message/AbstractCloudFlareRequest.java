package nhb.cf.api.v4.message;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.nhb.common.data.PuDataType;
import com.nhb.common.data.PuObject;
import com.nhb.common.data.PuValue;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class AbstractCloudFlareRequest implements CloudFlareRequest {

	@Setter(AccessLevel.PROTECTED)
	private String path;
	private String method;
	private Class<? extends CloudFlareResponse> responseType;

	{
		CloudFlareRequestMeta meta = null;
		Class<?> clazz = null;

		do {
			if (clazz == null) {
				clazz = this.getClass();
			} else if (clazz == AbstractCloudFlareRequest.class) {
				break;
			} else {
				clazz = clazz.getSuperclass();
			}
			meta = clazz.getAnnotation(CloudFlareRequestMeta.class);
		} while (meta == null);

		if (meta == null) {
			throw new NullPointerException("Meta annotation cannot be found in class " + this.getClass().getName());
		}

		this.path = meta.path() == null ? "" : meta.path();
		this.method = meta.method() == null ? "GET" : meta.method();
		this.responseType = meta.responseType() == null ? BaseCloudFlareResponse.class : meta.responseType();
	}

	protected void removeNullFields(PuObject data) {
		List<String> tobeRemoved = new LinkedList<>();
		for (Entry<String, PuValue> entry : data) {
			if (entry.getValue().getType() == PuDataType.NULL) {
				tobeRemoved.add(entry.getKey());
			}
		}
		for (String field : tobeRemoved) {
			data.remove(field);
		}
	}

	@Override
	public PuObject toPuObject() {
		return null;
	}
}
