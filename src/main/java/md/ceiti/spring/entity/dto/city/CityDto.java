package md.ceiti.spring.entity.dto.city;

import md.ceiti.spring.entity.Country;

public class CityDto {
        private Long cityId;
        private String cityName;
        private Country country;

    public CityDto(Long cityId, String cityName, Country country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
    }

    public Long getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public Country getCountry() {
        return country;
    }
}
