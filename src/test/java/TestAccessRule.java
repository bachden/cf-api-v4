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
			future = cloudFlareApi.unblockAnIp("202d1d37209945bcbb69a32454cdd7cb");

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
