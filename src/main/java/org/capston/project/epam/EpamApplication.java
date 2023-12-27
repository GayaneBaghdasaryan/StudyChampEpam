package org.capston.project.epam;

import org.capston.project.epam.config.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class})
public class EpamApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpamApplication.class, args);
    }

}
