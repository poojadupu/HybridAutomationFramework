package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {



     public HomePage(WebDriver driver){
       super(driver);
    }

    @FindBy(xpath="//section[@class='sp__main']/div/h1")
    WebElement homepage_heading;

     @FindBy(xpath="//a[text()='Auto Healing']")
     WebElement login;

    public void clickLogin() {
        login.click();

    }
     public String getHomepage_text(){
         String text=null;
       try {
            text= homepage_heading.getText();
       }
       catch(Exception e){
           e.getMessage();

         }
         return text;
     }



}
