package io.oppenheimer.test.steps;

import io.cucumber.java.en.Then;
import io.oppenheimer.test.pages.DashboardBasePage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DashboardSteps {

    protected final DashboardBasePage dashboardBasePage;

    @Then("verify dashboard title is {string}")
    public void veryDashboardTitle(String title) {
        Assertions.assertEquals(title, dashboardBasePage.getPageTitle());
    }
}
