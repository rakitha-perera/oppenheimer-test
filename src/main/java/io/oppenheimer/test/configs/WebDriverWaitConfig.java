package io.oppenheimer.test.configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties
public class WebDriverWaitConfig {

    @Bean
    @Scope("singleton")
    public WebDriverWait webdriverWait(WebDriver driver,
                                       @Value("${runner.timeout:10}") int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }
}
