package md.ceiti.spring.entity.dto.request;

import md.ceiti.spring.entity.City;
import md.ceiti.spring.entity.Country;

public class CityRequest {
    private String cityName;
    private Integer countryId;

    public CityRequest(String cityName, Integer country) {
        this.cityName = cityName;
        this.countryId = country;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public City toEntity(Country country){
        return new City(
                cityName,
                country
        );
    }

    public City toEntity(Integer id, Country country){
        return new City(
                id,
                cityName,
                country
        );
    }

}
