package nhb.cf.api.v4.message.firewall;

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
@CloudFlareRequestMeta(method = "DELETE", path = "zones/{{zoneId}}/firewall/access_rules/rules", responseType = DeleteZoneAccessRuleResponse.class)
public class DeleteZoneAccessRuleRequest extends AbstractResolvableCloudFlareRequest {

	private String identifier;

	@Override
	public String getPath() {
		return super.getPath() + "/" + this.getIdentifier();
	}
}
