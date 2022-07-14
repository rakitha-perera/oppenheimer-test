package io.oppenheimer.test.configuration;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;

import java.util.Collections;

@Configuration
public class SpringConfiguration {

    @Bean
    public CustomScopeConfigurer customScope () {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.setScopes(Collections.singletonMap("oppenheimer", new SimpleThreadScope()));
        return configurer;
    }
}
