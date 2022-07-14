package io.oppenheimer.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "username-in")
    public WebElement inputLoginUserName;

    @FindBy(id = "password-in")
    public WebElement inputLoginPassword;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement btnFormSubmit;

    By errorMsg = By.xpath("//div[@id='notification-block']//div[contains(@class, 'bg-danger') and text()='Unable to log in successfully!']");

    public void openLoginPage() {
        driver.get("http://localhost:9997/login");
    }

    public void login(String userName, String password) {
        inputLoginUserName.sendKeys(userName);
        inputLoginPassword.sendKeys(password);
        btnFormSubmit.click();
    }

    public boolean verifyErrorMessageIsDisplayed(){
        return !driver.findElements(errorMsg).isEmpty();
    }
}
