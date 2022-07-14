package io.oppenheimer.test.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("file-paths")
public class FilePathProperties {
    private String addUserCsv;
}
