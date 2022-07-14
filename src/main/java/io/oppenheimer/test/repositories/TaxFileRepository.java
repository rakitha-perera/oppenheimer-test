package io.oppenheimer.test.repositories;

import io.oppenheimer.test.models.jpa.TaxFileJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Optional;

@Repository
public interface TaxFileRepository extends JpaRepository<TaxFileJpa, Integer> {

    default Optional<TaxFileJpa> getLatestTaxFileRecord() {
        return findAll()
                .stream()
                .filter(file -> file.getFileType().equals("TAX_RELIEF"))
                .max(Comparator.comparing(TaxFileJpa::getId));
    }
}
