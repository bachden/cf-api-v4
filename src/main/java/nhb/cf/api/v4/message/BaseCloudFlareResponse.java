package nhb.cf.api.v4.message;

import java.util.LinkedList;
import java.util.List;

import com.nhb.common.data.PuArray;
import com.nhb.common.data.PuElement;
import com.nhb.common.data.PuObject;
import com.nhb.common.data.PuObjectRO;
import com.nhb.common.data.PuValue;

import lombok.Getter;

@Getter
public class BaseCloudFlareResponse implements CloudFlareResponse {

	private boolean successful = false;
	private final List<PuObject> errors = new LinkedList<>();
	private final List<PuObject> messages = new LinkedList<>();
	private PuElement result = null;

	private PuElement rawResponse;

	@Override
	public final void fromPuObject(PuObjectRO rawResponse) {
		this.rawResponse = rawResponse;
		this.successful = rawResponse.getBoolean("success", false);
		this.result = rawResponse.variableExists("result") ? rawResponse.get("result") : null;

		PuArray arr = rawResponse.getPuArray("errors", null);
		if (arr != null) {
			for (PuValue val : arr) {
				this.errors.add(val.getPuObject(null));
			}
		}

		arr = rawResponse.getPuArray("errors", null);
		if (arr != null) {
			for (PuValue val : arr) {
				this.messages.add(val.getPuObject(null));
			}
		}

		this.onResult(result);
	}

	protected void onResult(PuElement result) {

	}

	@Override
	public String toString() {
		return this.rawResponse == null ? null : this.rawResponse.toString();
	}
}
