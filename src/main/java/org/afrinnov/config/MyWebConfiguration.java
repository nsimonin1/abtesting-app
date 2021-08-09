package org.afrinnov.config;

import org.afrinnov.filter.AbtestFilter;
import org.ff4j.FF4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.Objects;

@Configuration(proxyBeanMethods = false)
public class MyWebConfiguration {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                Objects.requireNonNull(registry);
                registry.addMapping("/api/**")
                        .allowedMethods("PUT", "DELETE", "PATCH", "POST", "PUT", "GET");
            }
        };
    }

    @Bean
    public FilterRegistrationBean<Filter> abtestFilterRegistrationBean(FF4j ff4j) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(abtestFilter(ff4j));
        registration.addUrlPatterns("/*");
        registration.setName("abtestFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public Filter abtestFilter(FF4j getFF4j) {
        return new AbtestFilter(getFF4j);
    }
}
