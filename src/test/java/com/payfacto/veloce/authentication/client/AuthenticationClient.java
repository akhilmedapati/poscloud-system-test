package com.payfacto.veloce.authentication.client;

import static io.restassured.RestAssured.given;

import com.payfacto.veloce.authentication.fixtures.AuthenticationContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationClient {

  protected static final Logger LOGGER = Logger.getLogger(AuthenticationClient.class.getName());

  // @Value("${authentication.baseUrl}")
  private String baseUrl = "https://api.posveloce.com";

  /** Sends a post request to authenticate end point. */
  public Response sendAuthentication(AuthenticationContext authentication) {

    LOGGER.info("Auth Request" + authentication);

    Response response =
        given()
            .contentType(ContentType.JSON)
            .body(authentication).post(baseUrl + "/users/authenticate");

    LOGGER.info(" Authentication Response: " + response.asString());

    return response;
  }
}
