package md.ceiti.spring.controller;

import md.ceiti.spring.entity.dto.city.CityContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
