package io.oppenheimer.test.data;

import io.oppenheimer.test.models.jpa.TaxFileJpa;
import io.oppenheimer.test.models.rest.Hero;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Data
@Component
@Scope(value = "oppenheimer", proxyMode = TARGET_CLASS)
public class DynamicData {
    private TaxFileJpa latestTaxFileRecord;
    private List<Hero> listOfHeroesToBeDeleted = new ArrayList<>();

    public void addHeroTobeDeleted(Hero hero) {
        if (listOfHeroesToBeDeleted.stream().noneMatch(h -> h.getNatid().equals(hero.getNatid()))) {
            listOfHeroesToBeDeleted.add(hero);
        }
    }
}
