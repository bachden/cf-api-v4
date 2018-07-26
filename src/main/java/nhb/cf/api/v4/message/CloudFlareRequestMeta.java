package nhb.cf.api.v4.message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CloudFlareRequestMeta {

	String method() default "GET";

	String path();

	Class<? extends CloudFlareResponse> responseType() default BaseCloudFlareResponse.class;
}
