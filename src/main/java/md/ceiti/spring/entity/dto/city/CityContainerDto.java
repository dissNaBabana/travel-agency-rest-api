package md.ceiti.spring.entity.dto.city;

import md.ceiti.spring.entity.dto.tour.TourDto;

import java.util.List;

public class CityContainerDto {
    private final List<CityDto> cities;

    public CityContainerDto(List<CityDto> cities) {
        this.cities = cities;
    }

    public List<CityDto> getCities() {
        return cities;
    }
}
