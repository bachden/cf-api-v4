package nhb.cf.api.v4.message;

import com.nhb.common.utils.StringUtils;

import nhb.cf.api.v4.CloudFlareApiConfig;

public abstract class AbstractResolvableCloudFlareRequest extends AbstractCloudFlareRequest
		implements ResolvableRequest {

	@Override
	public void resolve(CloudFlareApiConfig config) {
		this.setPath(StringUtils.transform(this.getPath(), config));
	}
}
