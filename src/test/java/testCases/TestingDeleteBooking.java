package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojoClasses.CaptureBooking;
import testBase.BaseClass;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class TestingDeleteBooking extends BaseClass {
    @Test
    public void deleteBooking() throws FileNotFoundException {
        TestingCreateToken tc = new TestingCreateToken();
        tc.testingCreateToken();
        TestingCreateBooking cb = new TestingCreateBooking();
        cb.createBooking();
       String response = given().log().all()
                .header("Content-Type","application/json")
                .header("cookie","token="+token)
                .when().delete("booking/"+Integer.toString(bookingID))
                .then().log().all().assertThat().statusCode(201).extract().response().asString();
        Assert.assertTrue(response.equalsIgnoreCase("Created"));
    }
}
