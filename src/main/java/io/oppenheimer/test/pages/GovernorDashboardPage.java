package io.oppenheimer.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GovernorDashboardPage extends DashboardBasePage {

    public GovernorDashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//button[@type='submit' and @value='List ALL']")
    private WebElement listAllBtn;

    @FindBy(id = "natid")
    private WebElement inputNatIdSearch;

    private final By tableRows = By.xpath("//*[@id='search-all-table']/tbody/tr");
    private final By tableProcessingPopup = By.xpath("//div[@id='search-all-table_processing']");
    private final By inputSearchInListAll = By.xpath("//div[@id='search-all-table_filter']//input");
    private final By noOfEntriesSelect = By.xpath("//div[@id='search-all-table_wrapper']//select");

    public void listAll() {
        listAllBtn.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(tableProcessingPopup));
    }

    private int getTableRowCount() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(tableProcessingPopup));
        return driver.findElements(tableRows).size();
    }

    private boolean getNextPageAvailability() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(tableProcessingPopup));
        return !driver.findElement(By.id("search-all-table_next")).getAttribute("class").contains("disabled");
    }

    private void moveToNextPage() {
        String xpath = "//li[@id='search-all-table_next']/a";
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)", driver.findElement(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public Set<String> getAllHeroes() {
        String cellXpath = "//*[@id='search-all-table']/tbody/tr[%s]/td[%s]";
        List<String> heroes = new ArrayList<>();
        boolean isNextPageAvailable = true;

        Select select = new Select(driver.findElement(noOfEntriesSelect));
        select.selectByValue("50");

        while (isNextPageAvailable) {
            for (int row = 1; row <= getTableRowCount(); row++) {
                String natId = driver.findElement(By.xpath(String.format(cellXpath, row, 1))).getText();
                heroes.add(natId);
            }

            isNextPageAvailable = getNextPageAvailability();

            if (isNextPageAvailable) {
                moveToNextPage();
            }
        }

        return new HashSet<>(heroes);
    }

    public void searchHeroesByString(String searchString) {
        listAll();
        driver.findElement(inputSearchInListAll).sendKeys(searchString);
    }
}
