package org.capston.project.epam.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.capston.project.epam.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthUser {

  private UUID id;
  private String username;
  private String deviceId;
  private Set<String> authorities;

  public AuthUser(User user) {
    this.id = user.getId();
    this.username = user.getEmail();
    this.authorities = Collections.singleton("ROLE_USER");
  }
}
