package md.ceiti.spring.entity.dto.request;

import md.ceiti.spring.entity.Country;

public class CountryRequest {
    private final String countryName;

    public CountryRequest(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public Country toEntity(){
        return new Country(countryName);
    }
    public Country toEntity(Integer id, String countryName){
        return new Country(id, countryName);
    }
}
