package testCases;

import PageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomePageTest extends BasePageTest{
    HomePage h;


    @Test(groups="Regression")
    public void HomePage_text() {
        try {

            // Initialize HomePage object
            logger.info("Test HomePage_text started");
           h = new HomePage(driver);


            // Get the actual heading text
            String actualHeading = h.getHomepage_text();
            logger.info("Test passed - HomePage_text validation successful");

            String expectedHeading = "Selenium Playground";

            // Log the expected and actual values before the assertion
            logger.info("Test passed - HomePage_text validation successful");

            // Assert the values
            Assert.assertEquals(expectedHeading, actualHeading);

            // If assertion passes, log it
            logger.info("Test passed - HomePage_text validation successful");

        } catch (AssertionError e) {
            // Log assertion error with the message and stack trace
            // Log additional debugging information
            // Rethrow the exception to mark the test as failed
            logger.error("Test failed - HomePage_text validation failed", e);

            throw e;

        }
    }

}
