package org.capston.project.epam.security;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorizationFacadeImpl implements AuthorizationFacade {

  @Override
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @Override
  public UUID getUserId() {
    Authentication authentication = getAuthentication();
    AuthUser authUser = (AuthUser) authentication.getPrincipal();
    return authUser.getId();
  }

  @Override
  public AuthUser getAuthUser() {
    Authentication authentication = getAuthentication();
    return (AuthUser) authentication.getPrincipal();
  }
}
