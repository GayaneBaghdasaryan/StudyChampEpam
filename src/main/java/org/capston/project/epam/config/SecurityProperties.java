package org.capston.project.epam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("spring.security.client")
public class SecurityProperties {

  private String secretKey;
  private int accessTokenValiditySeconds;
  private int refreshTokenValiditySeconds;
}
