package io.oppenheimer.test.steps;

import com.google.common.collect.Sets;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.oppenheimer.test.data.DynamicData;
import io.oppenheimer.test.exceptions.RestApiException;
import io.oppenheimer.test.integrations.api.OppenheimerAPIClient;
import io.oppenheimer.test.models.jpa.HeroJpa;
import io.oppenheimer.test.models.rest.Hero;
import io.oppenheimer.test.pages.ClerkDashboardPage;
import io.oppenheimer.test.pages.GovernorDashboardPage;
import io.oppenheimer.test.properties.FilePathProperties;
import io.oppenheimer.test.repositories.HeroRepository;
import io.oppenheimer.test.utils.FileHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HeroesSteps {

    private final OppenheimerAPIClient oppenheimerAPIClient;
    private final HeroRepository heroRepository;
    private final ClerkDashboardPage clerkDashboardPage;
    private final GovernorDashboardPage governorDashboardPage;
    private final DynamicData dynamicData;
    private final FilePathProperties filePathProperties;

    @Given("The hero {string} does not exist in the database")
    public void verifyHeroDoesNotExistInDb(String natId) {
        if (heroRepository.findById(natId).isPresent()) {
            heroRepository.deleteById(natId);
        }
        Assertions.assertTrue(heroRepository.findById(natId).isEmpty());
    }

    @When("user calls the API to create a hero with values")
    public void createHeroViaAPI(List<Hero> heroes) throws RestApiException {
        for (Hero hero : heroes) {
            oppenheimerAPIClient.createHero(hero);
        }
        heroes.forEach(dynamicData::addHeroTobeDeleted);
    }

    @When("user creates the hero via UI with values {string} {string} {string} {string} {string} {string} {string} {string}")
    public void createHeroViaUI(String natId,
                                String name,
                                String gender,
                                String birthDate,
                                String deathDate,
                                String salary,
                                String taxPaid,
                                String browniePoints) {

        Hero hero = new Hero(natId, name, gender, birthDate, deathDate, salary, taxPaid, browniePoints);
        clerkDashboardPage.addUser(hero);
    }

    @Then("verify the hero {string} is exist in the database")
    public void verifyHeroExistInDb(String natId) throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertTrue(heroRepository.findById(natId).isPresent());
    }

    @When("user uploads a csv file with values")
    public void userUploadsValidCSVFile(List<Hero> heroes) throws IOException {
        heroes.forEach(dynamicData::addHeroTobeDeleted);
        String path = filePathProperties.getAddUserCsv();
        FileHelper.deleteFile(path);
        FileHelper.writeToCsv(path, heroes);
        clerkDashboardPage.uploadCsv(path);
    }

    @Then("verify system displays the created successfully message")
    public void verifyCreatedSuccessfullyMessage() {
        Assertions.assertTrue(clerkDashboardPage.isSuccessBlockDisplayed());
    }

    @Then("verify all the heroes are created in the database")
    public void verifyTheListOfHeroesAreCreated(List<String> heroIds) throws InterruptedException {
        for (String heroId : heroIds) {
            Assertions.assertTrue(heroRepository.findById(heroId).isPresent());
        }
    }

    @When("user lists all the heroes")
    public void userListsAllHeroes() {
        governorDashboardPage.listAll();
    }

    @Then("heroes table shows the correct records")
    public void heroesTableShowsTheCorrectNoOfHeroes() {
        Set<String> natIdsFromTable = heroRepository.findAll().stream().map(HeroJpa::getNatId).collect(Collectors.toSet());
        Set<String> natIdsOnUI = governorDashboardPage.getAllHeroes();

        Assertions.assertEquals(natIdsFromTable.size(), natIdsOnUI.size());

        Sets.SetView<String> missingHeroesOnUI = Sets.difference(natIdsFromTable, natIdsOnUI);
        log.warn("The following heroes (ids) are not displayed on the page:\n{}",
                missingHeroesOnUI
                        .stream()
                        .collect(Collectors.joining("\n", "* ", "")));
        Assertions.assertTrue(missingHeroesOnUI.isEmpty());
    }

    @When("user search for hero by {string}")
    public void userSearchForHeroByNatId(String searchString) {
        governorDashboardPage.searchHeroesByString(searchString);
    }

    @Then("heroes table shows the filtered records by {string}")
    public void heroesTableShowsFilteredRecords(String searchString) {
        Set<String> natIdsFromTable = heroRepository.getFilteredResult(searchString);
        Set<String> natIdsOnUI = governorDashboardPage.getAllHeroes();

        Assertions.assertEquals(natIdsFromTable.size(), natIdsOnUI.size());

        Sets.SetView<String> missingHeroesOnUI = Sets.difference(natIdsFromTable, natIdsOnUI);
        log.warn("The following heroes (ids) are not displayed on the page:\n{}",
                missingHeroesOnUI
                        .stream()
                        .collect(Collectors.joining("\n", "* ", "")));
        Assertions.assertTrue(missingHeroesOnUI.isEmpty());
    }

    @Then("verify only 1 user exist with id {string}")
    public void verifyOnlyOneUserExistInTheDBWithId(String natId) {
        Assertions.assertEquals(1, (long) heroRepository.findAllById(List.of(natId)).size());
    }

    @When("user calls the API to create a existing hero with values")
    public void userCallsTheAPIToCreateAExistingHeroWithValues(List<Hero> heroes) {
        for (Hero hero : heroes) {
            try {
                oppenheimerAPIClient.createHero(hero);
                dynamicData.addHeroTobeDeleted(hero); // Add to be deleted list in case hero is created
                Assertions.fail("Creating an existing hero should throw an error message!");
            } catch (RestApiException restApiException) {
                Assertions.assertEquals(restApiException.getStatusCode(), 400);
            }
        }
    }

    @Then("verify an error message is shown on dashboard")
    public void verifyLoginErrorMessageIsDisplayed(){
        Assertions.assertTrue(clerkDashboardPage.isWarningBlockDisplayed());
    }

    @Then("verify the error message {string} is shown")
    public void verifyTheErrorMessageIsShown(String errorMessage) {
        Assertions.assertTrue(clerkDashboardPage.isWarningBlockDisplayed());
        Assertions.assertTrue(clerkDashboardPage.getErrorMessage().contains(errorMessage));
    }
}
