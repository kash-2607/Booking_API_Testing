package testCases;


import org.testng.annotations.Test;
import pojoClasses.GetToken;
import testBase.BaseClass;

import java.io.FileNotFoundException;
import static io.restassured.RestAssured.given;

public class TestingCreateToken extends BaseClass {
    @Test
    public void testingCreateToken() throws FileNotFoundException {
        try {
            createTokenPojo.setUsername("admin");
            createTokenPojo.setPassword("password123");
            GetToken response = given()
                    .header("Content-Type","application/json")
                    .body(createTokenPojo)
                    .when().post("auth")
                    .then().log().all().assertThat().statusCode(200).extract().response().as(GetToken.class);
            token = response.getToken();
            logger.info("Token {}", token);
        }catch (Exception e){
            logger.error("Error occurred while logging request/response", e);
        }

    }


}
