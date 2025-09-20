package io.comeandcommue.lib.web.auth;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AuditorAwareConfiguration.class)
public @interface EnableAuditorAware {
}
