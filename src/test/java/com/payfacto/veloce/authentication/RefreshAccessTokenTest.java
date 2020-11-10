package com.payfacto.veloce.authentication;

import com.payfacto.veloce.authentication.client.AuthenticationClient;
import com.payfacto.veloce.authentication.client.RefreshTokenClient;
import com.payfacto.veloce.authentication.fixtures.AuthenticationContext;
import com.payfacto.veloce.authentication.validations.AuthenticationValidations;
import com.payfacto.veloce.authentication.validations.RefreshTokenValidations;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RefreshAccessTokenTest {

  @Autowired RefreshTokenValidations refreshTokenValidations;

  private RefreshTokenClient refreshTokenClient;

  private AuthenticationContext authenticationContext;

  private AuthenticationClient authenticationClient;

  @Autowired AuthenticationValidations authenticationValidations;

  @BeforeAll
  public void beforeAll() {
    authenticationContext = new AuthenticationContext();
    authenticationClient = new AuthenticationClient();
    refreshTokenClient = new RefreshTokenClient();
  }

  @Test
  public void refresh_validToken_approved() {

    authenticationContext.setEmail("Akhil.Medapati@payfacto.com");
    authenticationContext.setPassword("Akhil@0459");

    Response authResponse = authenticationClient.sendAuthentication(authenticationContext);
    authenticationValidations.validateValidAuthenticationCredentialsApiResponse(
        authResponse, authenticationContext);

    Response response =
        refreshTokenClient.sendRefreshToken(
            authResponse.jsonPath().get("token"),
            authResponse.jsonPath().get("id"));
    refreshTokenValidations.validateRefreshTokenApiResponse(response);
  }

}
