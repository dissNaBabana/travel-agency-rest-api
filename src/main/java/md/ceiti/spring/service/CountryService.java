package md.ceiti.spring.service;

import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.dto.country.CountryContainerDto;
import md.ceiti.spring.entity.dto.country.CountryDto;
import md.ceiti.spring.entity.dto.request.CountryRequest;
import md.ceiti.spring.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryContainerDto findAll(){
        List<CountryDto> countries = countryRepository.findAll().stream()
                .map(Country::toDto)
                .collect(Collectors.toList());
        return new CountryContainerDto(countries);
    }

    public CountryDto findById(Integer id){
        return countryRepository.findById(id)
                .map(Country::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Country with id" + id + "not founded"));
    }

    public CountryDto save(CountryRequest request){
        Country country = request.toEntity();
        return countryRepository.save(country).toDto();
    }

    public CountryDto update(Integer id, CountryRequest request){
        return countryRepository.findById(id)
                .map(existingCountry -> {
                    Country updatedCountry = request.toEntity(existingCountry.getCountryId(), request.getCountryName());
                    return countryRepository.save(updatedCountry).toDto();
                })
                .orElseThrow(() -> new IllegalArgumentException("Country with id" + id + "not founded"));
    }
}
