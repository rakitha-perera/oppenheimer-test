package io.oppenheimer.test.configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.oppenheimer.test.data.DynamicData;
import io.oppenheimer.test.models.rest.Hero;
import io.oppenheimer.test.repositories.HeroRepository;
import io.oppenheimer.test.repositories.TaxFileRepository;
import org.springframework.util.StringUtils;

import java.util.Map;

public class CucumberContextConfiguration {

    private final HeroRepository heroRepository;
    private final DynamicData dynamicData;

    public CucumberContextConfiguration(HeroRepository heroRepository, DynamicData dynamicData) {
        this.heroRepository = heroRepository;
        this.dynamicData = dynamicData;
    }

    @After
    public void cleanup() {
        dynamicData.getListOfHeroesToBeDeleted()
                .forEach(hero -> {
                    if (heroRepository.findById(hero.getNatid()).isPresent()) {
                        heroRepository.deleteById(hero.getNatid());
                    }
                });
    }

    @DataTableType
    public Hero heroEntry(Map<String, String> entry) {

        Hero hero = new Hero();

        hero.setNatid(entry.get("natid"));
        hero.setName(entry.get("name"));
        hero.setGender(entry.get("gender"));
        hero.setBirthDate(entry.get("birth_date"));
        hero.setSalary(Double.parseDouble(entry.get("salary")));
        hero.setTaxPaid(Integer.parseInt(entry.get("tax_paid")));

        if (StringUtils.hasText(entry.get("death_date"))) {
            hero.setDeathDate(entry.get("death_date"));
        }

        if (StringUtils.hasText(entry.get("brownie_points"))) {
            hero.setBrowniePoints(Integer.parseInt(entry.get("brownie_points")));
        }

        return hero;
    }
}
