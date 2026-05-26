package md.ceiti.spring.entity.dto.city;

import md.ceiti.spring.entity.Country;

public class CityDto {
        private Integer cityId;
        private String cityName;
        private Integer countryId;

    public CityDto(Integer cityId, String cityName, Integer countryId) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.countryId = countryId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getCountry() {
        return countryId;
    }
}
