package md.ceiti.spring.entity.dto.country;

public class CountryDto {
    private Integer countryId;
    private String countryName;

    public CountryDto(Integer countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }
}