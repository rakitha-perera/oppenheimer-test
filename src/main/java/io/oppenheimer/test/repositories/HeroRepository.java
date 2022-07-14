package io.oppenheimer.test.repositories;

import io.oppenheimer.test.models.jpa.HeroJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public interface HeroRepository extends JpaRepository<HeroJpa, String> {

    default Set<String> getFilteredResult(String searchString) {
        return findAll()
                .stream()
                .filter(hero -> hero.getNatId().contains(searchString) || hero.getName().contains(searchString))
                .map(HeroJpa::getNatId).collect(Collectors.toSet());
    }
}
