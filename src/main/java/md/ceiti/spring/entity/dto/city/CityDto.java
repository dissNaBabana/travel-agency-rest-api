package md.ceiti.spring.entity.dto.city;

import md.ceiti.spring.entity.Country;

public class CityDto {
        private Integer cityId;
        private String cityName;
        private Country country;

    public CityDto(Integer cityId, String cityName, Country country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public Country getCountry() {
        return country;
    }
}
