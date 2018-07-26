package nhb.cf.api.v4.message.firewall;

import com.nhb.common.data.PuObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nhb.cf.api.v4.message.AbstractResolvableCloudFlareRequest;
import nhb.cf.api.v4.message.CloudFlareRequestMeta;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@CloudFlareRequestMeta(method = "POST", path = "zones/{{zoneId}}/firewall/access_rules/rules", responseType = CreateZoneAccessRuleResponse.class)
public class CreateZoneAccessRuleRequest extends AbstractResolvableCloudFlareRequest {

	private String mode;
	private String target;
	private String value;

	@Builder.Default
	private String notes = "";

	@Override
	public PuObject toPuObject() {

		PuObject configuration = new PuObject();
		configuration.set("target", this.getTarget());
		configuration.set("value", this.getValue());

		PuObject result = new PuObject();
		result.set("mode", this.getMode());
		result.set("configuration", configuration);
		result.set("notes", this.getNotes());

		return result;
	}
}
