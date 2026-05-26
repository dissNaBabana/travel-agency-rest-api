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

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
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
        City city = request.toEntity();
        return cityRepository.save(city).toDto();
    }
}
