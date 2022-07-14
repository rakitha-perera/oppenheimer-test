package io.oppenheimer.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class BookKeeperDashboardPage extends DashboardBasePage {

    public BookKeeperDashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "tax_relief_btn")
    private WebElement generateTaxReliefFileBtn;

    public void generateTaxReliefFile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(generateTaxReliefFileBtn));
        generateTaxReliefFileBtn.click();
    }
}
