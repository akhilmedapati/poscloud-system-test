package com.payfacto.veloce.authentication.validations;

import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenValidations {

  /**
   * Method which validates the response of valid RefreshToken.
   *
   * @param response response object from RefreshToken end point
   */
  public void validateRefreshTokenApiResponse(Response response) {
    Assert.assertEquals(200, response.getStatusCode());
    Assert.assertNotNull(response.jsonPath().get("token"));
  }
}
