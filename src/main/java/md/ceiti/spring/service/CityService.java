package md.ceiti.spring.service;

import md.ceiti.spring.entity.City;
import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.dto.city.CityContainerDto;
import md.ceiti.spring.entity.dto.city.CityDto;
import md.ceiti.spring.entity.dto.country.CountryContainerDto;
import md.ceiti.spring.entity.dto.country.CountryDto;
import md.ceiti.spring.entity.dto.request.CityRequest;
import md.ceiti.spring.entity.dto.request.CountryRequest;
import md.ceiti.spring.repository.CityRepository;
import md.ceiti.spring.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public CityContainerDto findAll(){
        List<CityDto> cities = cityRepository.findAll().stream()
                .map(City::toDto)
                .collect(Collectors.toList());
        return new CityContainerDto(cities);
    }

    public CityDto findById(Integer id){
        return cityRepository.findById(id)
                .map(City::toDto)
                .orElseThrow(() -> new IllegalArgumentException("City with id" + id + "not founded"));
    }

    public CityDto save(CityRequest request){
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("City with id " + request.getCountryId() + " not found"));;
        City city = request.toEntity(country);
        return cityRepository.save(city).toDto();
    }

    public CityDto update(Integer id, CityRequest request){
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("City with id " + request.getCountryId() + " not found"));;
        return cityRepository.findById(id)
                .map(existingCity -> {
                    City updatedCity = request.toEntity(existingCity.getCityId(), country);
                    return cityRepository.save(updatedCity).toDto();
                })
                .orElseThrow(() -> new IllegalArgumentException("City with id" + id + "not founded"));
    }
}
