package com.mikhailkarpov.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

  private static final String USER_URL = "/api/v1/user";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getCurrentUserRedirect() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(USER_URL))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
  }

  @Test
  void getCurrentUserOk() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(USER_URL)
            .with(SecurityMockMvcRequestPostProcessors.oidcLogin()))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

}