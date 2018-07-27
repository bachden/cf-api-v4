package nhb.cf.api.v4.message.firewall;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nhb.cf.api.v4.CloudFlareApiConfig;
import nhb.cf.api.v4.message.AbstractResolvableCloudFlareRequest;
import nhb.cf.api.v4.message.CloudFlareRequestMeta;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@CloudFlareRequestMeta(method = "DELETE", path = "zones/{{zoneId}}/firewall/access_rules/rules", responseType = DeleteZoneAccessRuleResponse.class)
public class DeleteZoneAccessRuleRequest extends AbstractResolvableCloudFlareRequest {

	@Getter
	private String identifier;

	@Override
	public void resolve(CloudFlareApiConfig config) {
		super.resolve(config);
		this.setPath(this.getPath() + "/" + getIdentifier());
	}
}
