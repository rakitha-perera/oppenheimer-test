package io.oppenheimer.test.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.oppenheimer.test.pages.LoginPage;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class LoginSteps {

    private final LoginPage loginPage;

    @Given("user opens the login page")
    public void userOpensLoginPage() {
        loginPage.openLoginPage();
    }

    @When("user login with username {string} and password {string}")
    public void userEntersUserName(String userName, String password) {
        loginPage.login(userName, password);
    }

    @Then("verify an error message is shown")
    public void verifyLoginErrorMessageIsDisplayed(){
        Assertions.assertTrue(loginPage.verifyErrorMessageIsDisplayed());
    }
}
