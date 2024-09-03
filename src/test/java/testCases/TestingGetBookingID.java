package testCases;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.BaseClass;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestingGetBookingID extends BaseClass {
    @Test
    public void getBookingIDByFirstName(){
        TestingCreateBooking createBooking = new TestingCreateBooking();
        createBooking.createBooking();
         Response response = given().log().all()
                .param("firstname",firstname)
                .when().get("booking")
                .then().log().all().assertThat().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookingIds=  jsonPath.getList("bookingid");

        for (Integer id : bookingIds) {
            System.out.println(id);
        }
        Assert.assertFalse(bookingIds.isEmpty());
    }
    @Test
    public void getBookingIDByLastName(){
        TestingCreateBooking createBooking = new TestingCreateBooking();
        createBooking.createBooking();
        Response response = given().log().all()
                .param("lastname",lastname)
                .when().get("booking")
                .then().log().all().assertThat().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookingIds=  jsonPath.getList("bookingid");

        for (Integer id : bookingIds) {
            System.out.println(id);
        }
        Assert.assertFalse(bookingIds.isEmpty());
    }
    @Test
    public void getBookingIDByFirstNameAndLastName(){
        TestingCreateBooking createBooking = new TestingCreateBooking();
        createBooking.createBooking();
        Response response = given().log().all()
                .param("firstname",firstname)
                .param("lastname",lastname)
                .when().get("booking")
                .then().log().all().assertThat().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookingIds=  jsonPath.getList("bookingid");

        for (Integer id : bookingIds) {
            System.out.println(id);
        }
        Assert.assertFalse(bookingIds.isEmpty());
    }
    @Test
    public void getBookingIDByCheckInDate(){
        TestingCreateBooking createBooking = new TestingCreateBooking();
        createBooking.createBooking();
        Response response = given().log().all()
                .param("checkin",checkInDate)
                .when().get("booking")
                .then().log().all().assertThat().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookingIds=  jsonPath.getList("bookingid");

        for (Integer id : bookingIds) {
            System.out.println(id);
        }
        Assert.assertFalse(bookingIds.isEmpty());

    }
    @Test
    public void getBookingIDByCheckInDateAndCheckOutDate(){
        TestingCreateBooking createBooking = new TestingCreateBooking();
        createBooking.createBooking();
        Response response = given().log().all()
                .param("checkin",checkInDate)
                .param("checkout",checkOutDate)
                .when().get("booking")
                .then().log().all().assertThat().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookingIds=  jsonPath.getList("bookingid");

        for (Integer id : bookingIds) {
            System.out.println(id);
        }
        Assert.assertFalse(bookingIds.isEmpty());
    }



}
