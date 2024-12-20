package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//input[@id='username']")
    WebElement username;

    @FindBy(xpath="//input[@id='password']")
    WebElement password;

    @FindBy(xpath="//div[@class='flex items-center']//button[@type='submit']")
    WebElement submit;

    public void setUsername(String user){
        username.sendKeys(user);
    }

    public void setPassword(String pass){
        password.sendKeys(pass);
    }

    public void submit(){
        submit.click();
    }
}
