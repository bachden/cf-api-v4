import java.util.concurrent.ExecutionException;

import nhb.cf.api.v4.CloudFlareHelper;
import nhb.cf.api.v4.CloudFlareFuture;
import nhb.cf.api.v4.message.CloudFlareResponse;

public class TestAccessRule {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		try {
			CloudFlareHelper cloudFlareApi = CloudFlareHelper.newInstance(args[0], args[1], args[2]);
			final CloudFlareFuture future;

			// future = cloudFlareApi.blockAnIp("139.99.121.146");
			future = cloudFlareApi.unblockAnIp("1a9f5abdd92b43caa4f7faf6671ad31d");

			CloudFlareResponse response = future.get();
			if (response != null) {
				System.out.println("result: " + response);
			} else {
				future.getFailedCause().printStackTrace();
			}
		} finally {
			System.exit(0);
		}
	}
}

/*
 * result: BaseCloudFlareResponse(successful=true, errors=[], messages=[],
 * result={ modified_on:string = 2018-07-26T10:21:14.550154544Z, mode:string =
 * block, paused:boolean = false, notes:string = , configuration:puobject = {
 * value:string = 139.99.122.129, target:string = ip }, created_on:string =
 * 2018-07-26T10:21:14.550154544Z, scope:puobject = { name:string = bin.club,
 * id:string = d1f8a0f3ba8a88647118e5d1c6a0a6e4, type:string = zone },
 * allowed_modes:puarray = [whitelist, block, challenge, js_challenge],
 * id:string = b72f0d1790d649acadcdde3f3d73641f })
 */