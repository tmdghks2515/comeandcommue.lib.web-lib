package io.comeandcommue.lib.web.auth;

import io.comeandcommue.lib.web.baseEntity.ThreadLocalAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new ThreadLocalAuditorAware();
    }
}
