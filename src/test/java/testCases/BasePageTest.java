package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePageTest {

    public WebDriver driver;
    public Logger logger;
    TakesScreenshot takesScreenshot;

    public BasePageTest() {
        logger = LogManager.getLogger(this.getClass());

    }

     @BeforeMethod
    @Parameters({"browser","os","env"})

    public void setup(String bro,String OS,String env) throws IOException {



            FileInputStream file = new FileInputStream("C:\\Users\\desus\\IdeaProjects\\HybridAutomation\\config_"+env+".properties");
            Properties prop = new Properties();
            prop.load(file);

        if(prop.getProperty("execution_environment").equalsIgnoreCase("remote")){

            String hub_url=prop.getProperty("remote_url");
            DesiredCapabilities cap=new DesiredCapabilities();

            switch(OS.toLowerCase()){
                case "windows":cap.setPlatform(Platform.WIN11);
                break;

                case "linux":cap.setPlatform(Platform.LINUX);
                    break;


                default:return;

            }


//java -jar selenium-server-4.27.0.jar standalone-this should be run
// through cmd from the downloaded selenium server jar
//at the end you will get one hub url where you can see all different browser versions

 //for multiple nodes
 //java -jar selenium-server-4.27.0.jar hub-run on node machine
  //java -jar selenium-server-4.27.0.jar node-http://192.168.29.15:4444/wd/hub

            switch(bro.toLowerCase()) {


                case "chrome":
                    cap.setBrowserName("chrome");
                    break;


                case "firefox":
                    cap.setBrowserName("firefox");
                    break;


                case "edge":
                    cap.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    return;

            }

            driver=new RemoteWebDriver(new URL(hub_url),cap);




        }
        else {







            switch (bro.toLowerCase()) {

                case "chrome":
//                System.setProperty("webdriver.chrome.driver", "C:\\Users\\desus\\Downloads\\chromedriver_latest\\chromedriver-win64\\chromedriver.exe");
//                driver=new ChromeDriver();
                    ChromeOptions c=new ChromeOptions();
                    c.addArguments("--headless");
                    driver = new ChromeDriver(c);
                    break;
                case "edge":
//                System.setProperty("webdriver.edge.driver","C:\\Users\\desus\\Downloads\\edgedriver_win64\\msedgedriver.exe");
//                driver = new EdgeDriver();
                    driver = new EdgeDriver();

                    break;
                default:
                    System.out.println("check browser name");
                    return;//will come out of method and below wait and get will not excite


            }
        }


         String Url = prop.getProperty("url");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(Url);
          takesScreenshot = (TakesScreenshot) driver;
    }

    public String captureScreen() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"/screenShots/" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }




    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
