package org.capston.project.epam.security;

import java.util.UUID;
import org.springframework.security.core.Authentication;

public interface AuthorizationFacade {

  Authentication getAuthentication();

  UUID getUserId();

  AuthUser getAuthUser();
}
