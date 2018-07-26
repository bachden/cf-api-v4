package nhb.cf.api.v4.message;

import nhb.cf.api.v4.CloudFlareApiConfig;

public interface ResolvableRequest {
	void resolve(CloudFlareApiConfig config);
}
