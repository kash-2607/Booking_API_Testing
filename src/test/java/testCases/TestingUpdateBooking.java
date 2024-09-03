package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojoClasses.Booking;
import testBase.BaseClass;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class TestingUpdateBooking extends BaseClass {
    @Test
    public void testUpdateBooking() throws FileNotFoundException {
        TestingCreateToken tc = new TestingCreateToken();
        tc.testingCreateToken();
        TestingCreateBooking cb = new TestingCreateBooking();
        cb.createBooking();
        firstname = randomStringGenerator(5);
        booking.setFirstname(firstname);
        lastname = randomStringGenerator(5);
        booking.setLastname(lastname);
        booking.setTotalprice(generateRandomPrice());
        booking.setDepositpaid(true);
        booking.setBookingdates(bookingDates);
        String [] dates = generateFutureDates();
        checkInDate = dates[0];
        bookingDates.setCheckin(checkInDate);
        checkOutDate = dates[1];
        bookingDates.setCheckout(checkOutDate);
        booking.setAdditionalneeds(generateRandomNeeds());
        Booking response = given().log().all()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("cookie","token="+token)
                .body(booking)
                .when().put("booking/"+Integer.toString(bookingID))
                .then().log().all().assertThat().statusCode(200).extract().response().as(Booking.class);

    }
}
