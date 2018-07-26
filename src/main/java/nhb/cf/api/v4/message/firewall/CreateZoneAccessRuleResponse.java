package nhb.cf.api.v4.message.firewall;

import com.nhb.common.data.PuObject;

import lombok.Getter;
import lombok.ToString;
import nhb.cf.api.v4.message.BaseCloudFlareResponse;

@Getter
@ToString(callSuper = true)
public class CreateZoneAccessRuleResponse extends BaseCloudFlareResponse {

	private String identifier;

	@Override
	protected void onResult(PuObject result) {
		if (result != null) {
			this.identifier = result.getString("id", null);
		}
	}
}
