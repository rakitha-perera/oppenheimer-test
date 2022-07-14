package io.oppenheimer.test;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SpringBootTest
@CucumberContextConfiguration
@SelectClasspathResource("features")
public class TestCucumberFeaturesRunner {
}
