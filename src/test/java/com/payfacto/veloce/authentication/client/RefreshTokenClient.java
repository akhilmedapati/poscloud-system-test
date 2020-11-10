package com.payfacto.veloce.authentication.client;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenClient {

  protected static final Logger LOGGER = Logger.getLogger(RefreshTokenClient.class.getName());

  private String baseUrl = "https://api.posveloce.com";
  // @Value("${authentication.baseUrl}")
  // private String baseUrl;

  public Response sendRefreshToken(String refreshToken, String id) {

    LOGGER.info("Refresh Token: " + refreshToken + "Id: " + id);

    Response response =
        given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + refreshToken)
            .body(id)
            .post(baseUrl + "/users/refreshToken");

    LOGGER.info(" refreshToken Response: " + response.asString());

    return response;
  }
}
