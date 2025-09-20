package io.comeandcommue.lib.web.auth;

import io.comeandcommue.lib.web.baseEntity.ThreadLocalAuditorAware;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;


@Configuration
@RequiredArgsConstructor
public class AuthContextConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new ThreadLocalAuditorAware();
    }

    @Bean(name = "authContextFilter")
    public FilterRegistrationBean<RequestContextFilter> requestContextFilter() {
        FilterRegistrationBean<RequestContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestContextFilter());
        registration.setOrder(1); // 필요 시 조절
        return registration;
    }
}
