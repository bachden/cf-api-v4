package nhb.cf.api.v4.message.firewall;

import com.nhb.common.data.PuElement;
import com.nhb.common.data.PuObjectRO;

import lombok.Getter;
import lombok.ToString;
import nhb.cf.api.v4.message.BaseCloudFlareResponse;

@Getter
@ToString(callSuper = true)
public class CreateZoneAccessRuleResponse extends BaseCloudFlareResponse {

	private String identifier;

	@Override
	protected void onResult(PuElement result) {
		if (result != null && result instanceof PuObjectRO) {
			this.identifier = ((PuObjectRO) result).getString("id", null);
		}
	}
}
