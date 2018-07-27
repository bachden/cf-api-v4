package nhb.cf.api.v4;

import lombok.AllArgsConstructor;
import nhb.cf.api.v4.message.CloudFlareRequest;
import nhb.cf.api.v4.message.firewall.CreateZoneAccessRuleRequest;
import nhb.cf.api.v4.message.firewall.DeleteZoneAccessRuleRequest;
import nhb.cf.api.v4.message.firewall.ListZoneAccessRuleRequest;

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

	public CloudFlareFuture sendRequest(CloudFlareRequest req) {
		return this.api.send(req);
	}

	public CloudFlareFuture createZoneAccessRule(String mode, String target, String value, String notes) {
		return this.sendRequest(CreateZoneAccessRuleRequest.builder() //
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
		return this.sendRequest(DeleteZoneAccessRuleRequest.builder() //
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

	public CloudFlareFuture listAccessRule(ListZoneAccessRuleRequest req) {
		return this.sendRequest(req);
	}

	public CloudFlareFuture listBlockedIps(int page, int pageSize) {
		return this.listAccessRule(ListZoneAccessRuleRequest.builder() //
				.mode("block") //
				.page(page) //
				.pageSize(pageSize) //
				.build());
	}

	public CloudFlareFuture listBlockedIps(int page) {
		return this.listAccessRule(ListZoneAccessRuleRequest.builder() //
				.mode("block") //
				.page(page) //
				.build());
	}

	public CloudFlareFuture searchBlockedIpByNotes(String notes, int page, int pageSize) {
		return this.listAccessRule(ListZoneAccessRuleRequest.builder() //
				.notes(notes) //
				.mode("block") //
				.page(page) //
				.pageSize(pageSize) //
				.build());
	}

	public CloudFlareFuture searchBlockedIpByNotes(String notes, int page) {
		return this.listAccessRule(ListZoneAccessRuleRequest.builder() //
				.notes(notes) //
				.mode("block") //
				.page(page) //
				.build());
	}

	public CloudFlareFuture searchBlockedIp(String ip) {
		return this.searchBlockedIp(ip, 1);
	}

	public CloudFlareFuture searchBlockedIp(String ip, int page) {
		return this.listAccessRule(ListZoneAccessRuleRequest.builder() //
				.target("ip") //
				.value(ip) //
				.mode("block") //
				.page(page) //
				.build());
	}

	public CloudFlareFuture searchBlockedIp(String ip, int page, int pageSize) {
		return this.listAccessRule(ListZoneAccessRuleRequest.builder() //
				.target("ip") //
				.value(ip) //
				.mode("block") //
				.page(page) //
				.pageSize(pageSize) //
				.build());
	}
}
