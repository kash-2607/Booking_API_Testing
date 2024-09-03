package testBase;


import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import pojoClasses.*;


import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

public class BaseClass {

    public Logger logger;
    public static String token;
    public static String firstname;
    public static String lastname;
    public static String checkInDate;
    public static String checkOutDate;
    public static int bookingID;


    public String getProperties(String property) throws IOException {
        FileReader file = new FileReader("./src/test/resources/config.properties");
        Properties properties = new Properties();
        properties.load(file);
        return properties.getProperty(property);
    }

    @BeforeMethod
    public void setup() throws IOException {
        RestAssured.baseURI = getProperties("Base_URL");
        logger = LogManager.getLogger(this.getClass());
        PrintStream logFile = new PrintStream(new FileOutputStream("./logs/test.log",true));
        RestAssured.filters(new RequestLoggingFilter(logFile), new ResponseLoggingFilter(logFile));
    }

    public CreateToken createTokenPojo = new CreateToken();
    public GetToken getToken = new GetToken();
    public Booking booking = new Booking();
    public CaptureBooking captureBooking = new CaptureBooking();
    public BookingDates bookingDates = new BookingDates();

    //generate random string
    public String randomStringGenerator(int count) {
        String randomString = RandomStringUtils.randomAlphabetic(count);
        return randomString;
    }
    //generate random date
    public String[] generateFutureDates() {
        LocalDate currentDate = LocalDate.now();
        Random random = new Random();

        // Generate check-in date (0 to 60 days from now)
        LocalDate checkInDate = currentDate.plusDays(random.nextInt(61));

        // Generate check-out date (1 to 14 days after check-in)
        LocalDate checkOutDate = checkInDate.plusDays(random.nextInt(14) + 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return new String[]{
                checkInDate.format(formatter),
                checkOutDate.format(formatter)
        };
    }
        // Generate random additional needs
        public String generateRandomNeeds(){
            String[] needs = {
                    "Lunch","Dinner", "Lunch and Dinner", "Breakfast", "Breakfast and Lunch",
                    "Breakfast and Dinner", "All Meals"
            };
            Random random = new Random();
            return needs[random.nextInt(7)];
        }
        //Generate random price
    public int generateRandomPrice(){
        Random random = new Random();
        return random.nextInt(100,999);
    }

}
