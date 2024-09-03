package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojoClasses.Booking;
import testBase.BaseClass;

import static io.restassured.RestAssured.given;

public class TestingGetBooking extends BaseClass {
    @Test
    public void getBooking(){
    Booking response = given().log().all()
                .header("Accept", "application/json")
                .when().get("booking/"+"2269")
                .then().log().all().assertThat().statusCode(200).extract().response().as(Booking.class);
        Assert.assertEquals(response.getFirstname(),"David");
    }

}
