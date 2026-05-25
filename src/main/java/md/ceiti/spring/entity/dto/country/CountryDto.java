package md.ceiti.spring.entity.dto.country;

public class CountryDto {
    private Long countryId;
    private String countryName;

    public CountryDto(Long countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }
}