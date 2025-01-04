package testCases;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import Utilities.ExcelUtility;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginTestData extends BasePageTest{

    @Test(dataProvider="dp",groups={"Smoke"})

    public void LoginDataValidation(String user,String pass){
        HomePage h=new HomePage(driver);
        h.clickLogin();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LoginPage l=new LoginPage(driver);
        l.setUsername(user);
        l.setPassword(pass);
        l.submit();


    }

    @DataProvider(name="dp")
    public String[][] loginData() throws IOException {
        ExcelUtility excel=new ExcelUtility();
        excel.filereading();
        int cell=excel.cellcount();
        int row=excel.getrowcount();
       return excel.getCellData(cell,row);

    }


}
