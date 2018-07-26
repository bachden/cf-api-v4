package nhb.cf.api.v4;

import lombok.AllArgsConstructor;
import nhb.cf.api.v4.message.firewall.CreateZoneAccessRuleRequest;
import nhb.cf.api.v4.message.firewall.DeleteZoneAccessRuleRequest;

@AllArgsConstructor
public class CloudFlareHelper {

	public static CloudFlareHelper newInstance(String email, String key, String zoneId) {
		CloudFlareApiConfig config = CloudFlareApiConfig.builder() //
				.email(email) //
				.key(key) //
				.zoneId(zoneId) //
				.build();
		return newInstance(config);
	}

	public static CloudFlareHelper newInstance(CloudFlareApiConfig config) {
		return new CloudFlareHelper(CloudFlareApi.newDefault(config));
	}

	private final CloudFlareApi api;

	public CloudFlareFuture createZoneAccessRule(String mode, String target, String value, String notes) {
		return this.api.send(CreateZoneAccessRuleRequest.builder() //
				.mode(mode) //
				.target(target) //
				.value(value) //
				.notes(notes) //
				.build());
	}

	public CloudFlareFuture blockAnIp(String ip, String notes) {
		return this.createZoneAccessRule("block", "ip", ip, notes);
	}

	public CloudFlareFuture blockAnIp(String ip) {
		return this.blockAnIp(ip, "");
	}

	public CloudFlareFuture deleteZoneAccessRule(String identifier) {
		return this.api.send(DeleteZoneAccessRuleRequest.builder() //
				.identifier(identifier) //
				.build());
	}

	/**
	 * unblock an ip by blocking id
	 * 
	 * @param blockingId
	 *            take from result of create zone access rule request (or blockAnIp)
	 * @return
	 */
	public CloudFlareFuture unblockAnIp(String blockingId) {
		return this.deleteZoneAccessRule(blockingId);
	}
}
