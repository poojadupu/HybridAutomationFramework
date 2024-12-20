package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testCases.BasePageTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

        ExtentSparkReporter extentSparkReporter;
        ExtentReports extentreports;
        ExtentTest extentTest;


        @Override
        public void onStart(ITestContext context) {

            String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mmm.ss").format(new Date());
            extentSparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+timeStamp+".html");
            extentSparkReporter.config().setDocumentTitle("Automation Test Report");
            extentSparkReporter.config().setReportName("Functional Testing");
            extentSparkReporter.config().setTheme(Theme.STANDARD);


            extentreports=new ExtentReports();
            extentreports.attachReporter(extentSparkReporter);

            extentreports.setSystemInfo("computer Name","Local Host");

            String operating=context.getCurrentXmlTest().getParameter("os");

            extentreports.setSystemInfo("Os",operating);

            String brow=context.getCurrentXmlTest().getParameter("browser");
            extentreports.setSystemInfo("browser",brow);

            extentreports.setSystemInfo("TESTER","Pooja");
            extentreports.setSystemInfo("Environment","SQA");




        }

        public void onTestSuccess(ITestResult result){
            extentTest=extentreports.createTest(result.getName());

            extentTest.log(Status.PASS,"Test case got passed");



        }

        public void onTestFailure(ITestResult result){
            extentTest=extentreports.createTest(result.getName());
            extentTest.log(Status.FAIL,"test case got failed"+result.getName());
            extentTest.log(Status.FAIL,"test failed because of"+result.getThrowable());
//
//            try {
//                String imgPath = new BasePageTest().captureScreen();
//                extentTest.addScreenCaptureFromPath(imgPath);
//
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }

        }

        public void onFinish(ITestContext context){
            extentreports.flush();;
        }
    }


