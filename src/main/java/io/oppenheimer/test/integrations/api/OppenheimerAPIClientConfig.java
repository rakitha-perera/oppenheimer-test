package io.oppenheimer.test.integrations.api;

import io.oppenheimer.test.properties.OppenheimerAPIProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OppenheimerAPIClientConfig {

    @Bean
    public OppenheimerAPIClient getOppenheimerAPIClient(OppenheimerAPIProperties oppenheimerAPIProperties) {
        return new OppenheimerAPIClient(oppenheimerAPIProperties);
    }
}
