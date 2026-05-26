package md.ceiti.spring.controller;

import md.ceiti.spring.entity.dto.country.CountryContainerDto;
import md.ceiti.spring.entity.dto.country.CountryDto;
import md.ceiti.spring.entity.dto.request.CountryRequest;
import md.ceiti.spring.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public CountryContainerDto findAll(){
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public CountryDto findById(@PathVariable Integer id){

        return countryService.findById(id);
    }

    @PostMapping()
    public CountryDto save( @RequestBody CountryRequest request){
        return countryService.save(request);
    }

    @PutMapping("/{id}")
    public CountryDto update(@PathVariable Integer id, @RequestBody CountryRequest request){
        return countryService.update(id, request);
    }

}
