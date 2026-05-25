package md.ceiti.spring.service;

import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.dto.country.CountryContainerDto;
import md.ceiti.spring.entity.dto.country.CountryDto;
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
}
