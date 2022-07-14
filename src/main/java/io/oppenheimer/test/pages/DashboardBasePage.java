package io.oppenheimer.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class DashboardBasePage extends BasePage {

    public DashboardBasePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//div//h1")
    private WebElement pageTitle;

    public String getPageTitle() {
        return pageTitle.getText();
    }
}
