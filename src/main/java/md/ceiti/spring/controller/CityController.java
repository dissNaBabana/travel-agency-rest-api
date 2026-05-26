package md.ceiti.spring.controller;

import md.ceiti.spring.entity.dto.city.CityContainerDto;
import md.ceiti.spring.entity.dto.city.CityDto;
import md.ceiti.spring.entity.dto.country.CountryDto;
import md.ceiti.spring.entity.dto.request.CityRequest;
import md.ceiti.spring.entity.dto.request.CountryRequest;
import md.ceiti.spring.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping()
    public CityContainerDto findAll(){
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public CityDto findById(@PathVariable Integer id){

        return cityService.findById(id);
    }

    @PostMapping()
    public CityDto save( @RequestBody CityRequest request){
        return cityService.save(request);
    }

}
