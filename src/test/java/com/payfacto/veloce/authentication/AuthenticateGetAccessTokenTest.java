package com.payfacto.veloce.authentication;

import com.payfacto.veloce.authentication.client.AuthenticationClient;
import com.payfacto.veloce.authentication.fixtures.AuthenticationContext;
import com.payfacto.veloce.authentication.validations.AuthenticationValidations;
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
public class AuthenticateGetAccessTokenTest {

  @Autowired
  AuthenticationValidations authenticationValidations;

  @Autowired
  AuthenticationClient authenticationClient;

  private AuthenticationContext authenticationContext;

  @BeforeAll
  public void beforeAll() {
    authenticationContext = new AuthenticationContext();
  }

  @Test
  public void authenticate_validCredentials_approvedLogin() {

    authenticationContext.setEmail("Akhil.Medapati@payfacto.com");
    authenticationContext.setPassword("Akhil@0459");

    Response response = authenticationClient.sendAuthentication(authenticationContext);
    authenticationValidations.validateValidAuthenticationCredentialsApiResponse(
        response, authenticationContext);
  }

  @Test
  public void authenticate_invalidCredentials_failedLogin() {

    authenticationContext.setEmail("akhil1.medapati@payfacto.com");
    authenticationContext.setPassword("Akhil@049");

    Response response = authenticationClient.sendAuthentication(authenticationContext);
    authenticationValidations.validateErrorResponse(response, "404", "User not found");
  }

  @Test
  public void authenticate_invalidPassword_failedLogin() {

    authenticationContext.setEmail("akhil.medapati@payfacto.com");
    authenticationContext.setPassword("Akhil@049");

    Response response = authenticationClient.sendAuthentication(authenticationContext);
    authenticationValidations.validateErrorResponse(response, "500", "Could not login user");
  }

  @Test
  public void authenticate_invalidUserName_failedLogin() {

    authenticationContext.setEmail("akhil1.medapati@payfacto.com");
    authenticationContext.setPassword("Akhil@0459");

    Response response = authenticationClient.sendAuthentication(authenticationContext);
    authenticationValidations.validateErrorResponse(response, "404", "User not found");
  }

  @Test
  public void authenticate_nullUserName_failedLogin() {

    authenticationContext.setEmail(" ");
    authenticationContext.setPassword("Akhil@0459");

    Response response = authenticationClient.sendAuthentication(authenticationContext);
    authenticationValidations.validateErrorResponse(response, "404", "User not found");
  }

  @Test
  public void authenticate_nullPassword_failedLogin() {

    authenticationContext.setEmail("akhil1.medapati@payfacto.com");
    authenticationContext.setPassword(" ");

    Response response = authenticationClient.sendAuthentication(authenticationContext);
    authenticationValidations.validateErrorResponse(response, "404", "User not found");
  }
}
