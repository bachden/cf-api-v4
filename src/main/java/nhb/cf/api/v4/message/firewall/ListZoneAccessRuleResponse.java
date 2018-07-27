package nhb.cf.api.v4.message.firewall;

import java.util.LinkedList;
import java.util.List;

import com.nhb.common.data.PuArray;
import com.nhb.common.data.PuArrayList;
import com.nhb.common.data.PuElement;
import com.nhb.common.data.PuObject;
import com.nhb.common.data.PuObjectRO;
import com.nhb.common.data.PuValue;

import lombok.Getter;
import lombok.ToString;
import nhb.cf.api.v4.message.BaseCloudFlareResponse;

@Getter
@ToString(callSuper = true)
public class ListZoneAccessRuleResponse extends BaseCloudFlareResponse {

	@Getter
	@ToString
	public static class AccessRule {
		private String id;
		private String modifiedOn;
		private String mode;
		private boolean paused;
		private String notes;
		private String createdOn;
		private final List<String> allowedModes = new LinkedList<>();

		private String target;
		private String value;

		private String scopeName;
		private String scopeId;
		private String scopeType;

		private AccessRule() {
			// hide constructor
		}

		private static AccessRule fromPuObject(PuObject puo) {
			AccessRule result = new AccessRule();
			result.id = puo.getString("id", null);
			result.modifiedOn = puo.getString("modified_on", null);
			result.mode = puo.getString("mode", null);
			result.paused = puo.getBoolean("paused", false);
			result.notes = puo.getString("notes", null);
			result.createdOn = puo.getString("created_on", null);

			PuObject scope = puo.getPuObject("scope", new PuObject());
			result.scopeId = scope.getString("id", null);
			result.scopeName = scope.getString("name", null);
			result.scopeType = scope.getString("type", null);

			PuObject configuration = puo.getPuObject("configuration", new PuObject());
			result.target = configuration.getString("target", null);
			result.value = configuration.getString("value", null);

			PuArray allowedModes = puo.getPuArray("allowed_modes", new PuArrayList());
			for (PuValue val : allowedModes) {
				result.allowedModes.add(val.getString(null));
			}
			return result;
		}
	}

	private final List<AccessRule> accessRules = new LinkedList<>();

	private int page = -1;
	private int pageSize = -1;
	private int totalCount = -1;
	private int totalPages = -1;
	private int count = -1;

	@Override
	protected void onResult(PuElement result) {
		if (result != null && result instanceof PuArray) {
			for (PuValue val : (PuArray) result) {
				AccessRule ar = AccessRule.fromPuObject(val.getPuObject(null));
				if (ar != null) {
					this.accessRules.add(ar);
				}
			}
		}

		if (this.getRawResponse() != null && this.getRawResponse() instanceof PuObjectRO) {
			PuObject resultInfo = ((PuObjectRO) this.getRawResponse()).getPuObject("result_info", null);
			if (resultInfo != null) {
				this.page = resultInfo.getInteger("page", -1);
				this.pageSize = resultInfo.getInteger("per_page", -1);
				this.totalCount = resultInfo.getInteger("total_count", -1);
				this.totalPages = resultInfo.getInteger("total_pages", -1);
				this.count = resultInfo.getInteger("count", -1);
			}
		}
	}
}
