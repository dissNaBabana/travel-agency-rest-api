package md.ceiti.spring.entity.dto.country;

import md.ceiti.spring.entity.dto.tour.TourDto;

import java.util.List;

public class CountryContainerDto {
    private final List<CountryDto> countries;

    public CountryContainerDto(List<CountryDto> countries) {
        this.countries = countries;
    }

    public List<CountryDto> getCountries() {
        return countries;
    }
}
