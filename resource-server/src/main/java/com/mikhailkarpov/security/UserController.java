package com.mikhailkarpov.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping("/api/v1/user")
  public UserDetails getCurrentUser(@AuthenticationPrincipal OidcUser oidcUser) {
    return new UserDetails(
        oidcUser.getSubject(),
        oidcUser.getFullName()
    );
  }
}
