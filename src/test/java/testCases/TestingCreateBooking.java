package testCases;

import org.testng.annotations.Test;
import pojoClasses.CaptureBooking;
import testBase.BaseClass;

import static io.restassured.RestAssured.given;

public class TestingCreateBooking extends BaseClass {

    @Test
    public void createBooking(){
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
        CaptureBooking response = given().log().all()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(booking)
                .when().post("booking")
                .then().log().all().assertThat().statusCode(200).extract().response().as(CaptureBooking.class);
        bookingID = response.getBookingid();

    }

}
