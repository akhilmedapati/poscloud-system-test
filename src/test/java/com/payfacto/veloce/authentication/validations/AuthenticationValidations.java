package com.payfacto.veloce.authentication.validations;

import com.payfacto.veloce.authentication.fixtures.AuthenticationContext;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationValidations {

  /**
   * Validates mandatory response parameters from Authentication post request.
   *
   * @param response response object form Authentication end point
   */
  public void validateValidAuthenticationCredentialsApiResponse(
      Response response, AuthenticationContext authenticationContext) {
    Assert.assertEquals(200, response.getStatusCode());
    Assert.assertEquals(authenticationContext.getEmail(), response.jsonPath().get("email"));
  }

  /**
   * Method which validates error response.
   *
   * @param response response object from Authentication end point
   */
  public void validateErrorResponse(
      Response response, String expectedStatusCode, String expectedErrorResponse) {
    Assert.assertEquals(Integer.parseInt(expectedStatusCode), response.getStatusCode());
    Assert.assertEquals(expectedErrorResponse, response.getBody().asString());
  }
}
