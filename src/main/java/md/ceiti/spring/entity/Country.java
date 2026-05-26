package md.ceiti.spring.entity;

import jakarta.persistence.*;
import md.ceiti.spring.entity.dto.country.CountryDto;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;

    public Country() {
    }
    public Country(String countryName) {
        this.countryName = countryName;

    }
    public Country(Integer countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }



    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public CountryDto toDto(){
        return new CountryDto(
                countryId,
                countryName);
    }
}
