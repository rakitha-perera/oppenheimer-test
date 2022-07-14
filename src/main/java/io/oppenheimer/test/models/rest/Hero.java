package io.oppenheimer.test.models.rest;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
    @CsvBindByPosition(position = 0)
    private String natid;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private String gender;

    @CsvBindByPosition(position = 3)
    private String birthDate;

    @CsvBindByPosition(position = 4)
    private String deathDate;

    @CsvBindByPosition(position = 5)
    private double salary;

    @CsvBindByPosition(position = 6)
    private double taxPaid;

    @CsvBindByPosition(position = 7)
    private Integer browniePoints;

    public Hero(String natId,
                String name,
                String gender,
                String birthDate,
                String deathDate,
                String salary,
                String taxPaid,
                String browniePoints) {

        this.natid = natId;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.salary = Double.parseDouble(salary);
        this.taxPaid = Double.parseDouble(taxPaid);

        try {
            this.browniePoints = Integer.parseInt(browniePoints);
        } catch (NumberFormatException ex) {
            this.browniePoints = 0;
        }
    }
}
