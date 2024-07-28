package com.mikhailkarpov.security;

import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping("/api/v1/user")
  public Map<String, Object> getCurrentUser(@AuthenticationPrincipal OidcUser oidcUser) {
    return oidcUser.getClaims();
  }
}
