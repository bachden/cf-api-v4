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
@CloudFlareRequestMeta(method = "GET", path = "zones/{{zoneId}}/firewall/access_rules/rules", responseType = ListZoneAccessRuleResponse.class)
public class ListZoneAccessRuleRequest extends AbstractResolvableCloudFlareRequest {

	@Builder.Default
	private String notes = null;

	@Builder.Default
	private String mode = null;

	@Builder.Default
	private String match = null;

	@Builder.Default
	private String target = null;
	private String value;

	@Builder.Default
	private String order = null;

	@Builder.Default
	private int page = 1;

	@Builder.Default
	private int pageSize = 30;

	@Builder.Default
	private boolean asc = false;

	@Override
	public PuObject toPuObject() {
		PuObject result = new PuObject();
		result.set("notes", this.getNotes());
		result.set("mode", this.getMode());
		result.set("match", this.getMatch());
		result.set("configuration.target", this.getTarget());
		result.set("configuration.value", this.getValue());
		result.set("order", this.getOrder());
		result.set("page", this.getPage());
		result.set("per_page", this.getPageSize());
		result.set("direction", this.isAsc() ? "asc" : "desc");

		this.removeNullFields(result);
		return result;
	}
}
