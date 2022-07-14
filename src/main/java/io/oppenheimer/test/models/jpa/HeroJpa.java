package io.oppenheimer.test.models.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "working_class_heroes")
public class HeroJpa {

    @Id
    private String natid;
    private String name;
    private String gender;
    private String birthDate;
    private String deathDate;
    private double salary;
    private double taxPaid;
    private Integer browniePoints; //nullable

    public HeroJpa() {
    }

    public HeroJpa(String natid,
                   String name,
                   String gender,
                   String birthDate,
                   String deathDate,
                   double salary,
                   double taxPaid,
                   Integer browniePoints) {
        this.natid = natid;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.salary = salary;
        this.taxPaid = taxPaid;
        this.browniePoints = browniePoints;
    }

    @Column(name = "natid", nullable = false)
    public String getNatId() {
        return natid;
    }

    public void setNatId(String natid) {
        this.natid = natid;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "gender", nullable = false)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "birth_date", nullable = false)
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "death_date")
    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    @Column(name = "salary", nullable = false)
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Column(name = "tax_paid", nullable = false)
    public double getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(double taxPaid) {
        this.taxPaid = taxPaid;
    }

    @Column(name = "brownie_points")
    public Integer getBrowniePoints() {
        return browniePoints;
    }

    public void setBrowniePoints(int browniePoints) {
        this.browniePoints = browniePoints;
    }
}
