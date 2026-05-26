package md.ceiti.spring.entity.dto.request;

import md.ceiti.spring.entity.City;
import md.ceiti.spring.entity.Country;

public class CityRequest {
    private String cityName;
    private Country country;

    public CityRequest(String cityName, Country country) {
        this.cityName = cityName;
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public Country getCountry() {
        return country;
    }

    public City toEntity(){
        return new City(
                cityName,
                country
        );
    }

    public City toEntity(Integer id){
        return new City(
                cityName,
                country
        );
    }

}
