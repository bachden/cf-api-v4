package nhb.cf.api.v4;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CloudFlareApiConfig {

	private final String apiEndpoint = "https://api.cloudflare.com/client/v4";

	private final String email;

	private final String key;

	private final String zoneId;
}
