package io.oppenheimer.test.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.oppenheimer.test.data.DynamicData;
import io.oppenheimer.test.models.jpa.TaxFileJpa;
import io.oppenheimer.test.pages.BookKeeperDashboardPage;
import io.oppenheimer.test.repositories.HeroRepository;
import io.oppenheimer.test.repositories.TaxFileRepository;
import io.oppenheimer.test.utils.FileHelper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TaxReliefSteps {

    private final TaxFileRepository taxFileRepository;
    private final HeroRepository heroRepository;
    private final DynamicData dynamicData;
    private final BookKeeperDashboardPage bookKeeperDashboardPage;

    @When("user clicks on Generate Tax Relief File button")
    public void generateTaxReliefFile() {
        taxFileRepository
                .getLatestTaxFileRecord()
                .ifPresent(dynamicData::setLatestTaxFileRecord);
        bookKeeperDashboardPage.generateTaxReliefFile();
    }

    @Then("tax relief file is downloaded and has correct data")
    public void verifyTaxReliefFileIsDownloaded() throws IOException {
        String taxFilePath = dynamicData.getLatestTaxFileRecord().getFilePath();
        Assertions.assertTrue(FileHelper.fileExist(taxFilePath));

        List<String> taxFileContent = FileHelper.readFileContent(taxFilePath);
        Assertions.assertEquals(Long.parseLong(taxFileContent.get(taxFileContent.size() - 1)), heroRepository.count());
    }

    @Then("a new record is added to the database with complete status")
    public void verifyNewRecordIsAddedToTheDatabase() throws InterruptedException {
        Thread.sleep(3000);
        Optional<TaxFileJpa> mbTaxFile = taxFileRepository.getLatestTaxFileRecord();
        Assertions.assertTrue(mbTaxFile.isPresent());
        TaxFileJpa oldTaxFile = dynamicData.getLatestTaxFileRecord();

        if (oldTaxFile != null) {
            Assertions.assertTrue(oldTaxFile.getId() < mbTaxFile.get().getId());
        }

        dynamicData.setLatestTaxFileRecord(mbTaxFile.get());
    }
}
