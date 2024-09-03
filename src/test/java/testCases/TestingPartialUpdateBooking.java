package testCases;

import org.testng.annotations.Test;
import pojoClasses.Booking;
import testBase.BaseClass;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class TestingPartialUpdateBooking extends BaseClass {
    @Test
    public void partialUpdateBooking() throws FileNotFoundException {
        TestingCreateToken createToken = new TestingCreateToken();
        createToken.testingCreateToken();
        TestingCreateBooking createBooking = new TestingCreateBooking();
        createBooking.createBooking();
        Booking response = given().log().all()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("cookie","token="+token)
                .body("{\n" +
                        "    \"firstname\" : \"James\",\n" +
                        "    \"lastname\" : \"Brown\"\n" +
                        "}")
                .when().patch("booking/"+Integer.toString(bookingID))
                .then().log().all().assertThat().statusCode(200).extract().response().as(Booking.class);

    }
}
