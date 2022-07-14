package io.oppenheimer.test.pages;

import io.oppenheimer.test.models.rest.Hero;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ClerkDashboardPage extends DashboardBasePage {

    public ClerkDashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "natid-in")
    private WebElement natIdInput;

    @FindBy(id = "name-in")
    private WebElement nameInput;

    @FindBy(id = "gender-radio-1")
    private WebElement maleRadioBtn;

    @FindBy(id = "gender-radio-2")
    private WebElement femaleRadioBtn;

    @FindBy(id = "birth-date")
    private WebElement birthDateInout;

    @FindBy(id = "death-date")
    private WebElement deathDateInout;

    @FindBy(id = "brownie-pts-in")
    private WebElement browniePointsInput;

    @FindBy(id = "salary-in")
    private WebElement salaryInput;

    @FindBy(id = "tax-paid-in")
    private WebElement taxPaid;

    @FindBy(id = "upload-csv-file")
    private WebElement fileSelectorInput;

    @FindBy(id = "dropdownMenuButton2")
    private WebElement addHeroDropDown;

    @FindBy(linkText = "Add")
    private WebElement addUserListItem;

    @FindBy(linkText = "Upload a csv file")
    private WebElement uploadCSVListItem;

    @FindBy(xpath = "//button[text()='Create']")
    private WebElement createBtn;

    private final String notificationBlockBasePath = "//div[@id='notification-block']";
    private final By warningNotificationBlock = By.xpath(notificationBlockBasePath + "/div[@class='bg-warning pt-2']");
    private final By errorMessage = By.xpath(notificationBlockBasePath + "//div[@class='col-12'][1]/p");
    private final By successNotificationBlock = By.xpath(notificationBlockBasePath + "/div[@class='bg-success pt-2']");

    public void addUser(Hero hero) {
        addHeroDropDown.click();
        addUserListItem.click();
        wait.until(ExpectedConditions.elementToBeClickable(natIdInput));

        natIdInput.sendKeys(hero.getNatid());
        nameInput.sendKeys(hero.getName());

        if ("MALE".equals(hero.getGender())) {
            maleRadioBtn.click();
        } else {
            femaleRadioBtn.click();
        }

        birthDateInout.sendKeys(hero.getBirthDate());

        if (StringUtils.hasText(hero.getDeathDate())) {
            deathDateInout.sendKeys(hero.getDeathDate());
        }

        salaryInput.sendKeys(Double.toString(hero.getSalary()));
        taxPaid.sendKeys(Double.toString(hero.getTaxPaid()));

        if (hero.getBrowniePoints() != 0) {
            browniePointsInput.sendKeys(Integer.toString(hero.getBrowniePoints()));
        }

        createBtn.click();
    }

    public void uploadCsv(String filePath) {
        addHeroDropDown.click();
        uploadCSVListItem.click();
        fileSelectorInput.sendKeys(filePath);
        createBtn.click();
    }

    public boolean isWarningBlockDisplayed() {
        wait.until(ExpectedConditions.elementToBeClickable(warningNotificationBlock));
        return driver.findElement(warningNotificationBlock).isDisplayed();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(warningNotificationBlock));
        return driver.findElement(errorMessage).getText();
    }

    public boolean isSuccessBlockDisplayed() {
        wait.until(ExpectedConditions.elementToBeClickable(successNotificationBlock));
        return driver.findElement(successNotificationBlock).isDisplayed();
    }
}
