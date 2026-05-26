package md.ceiti.spring.controller;

import md.ceiti.spring.entity.dto.city.CityDto;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.entity.dto.request.CityRequest;
import md.ceiti.spring.entity.dto.request.HotelRequest;
import md.ceiti.spring.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping()
    public HotelContainerDto findAll(){
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    public HotelDto findById(@PathVariable Integer id){

        return hotelService.findById(id);
    }

    @PostMapping()
    public HotelDto save( @RequestBody HotelRequest request){
        return hotelService.save(request);
    }

    @PutMapping("/{id}")
    public HotelDto update(@PathVariable Integer id, @RequestBody HotelRequest request){
        return hotelService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        hotelService.delete(id);
    }

}
