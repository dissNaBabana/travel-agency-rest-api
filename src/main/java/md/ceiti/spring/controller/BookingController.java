package md.ceiti.spring.controller;

import md.ceiti.spring.entity.dto.booking.BookingContainerDto;
import md.ceiti.spring.entity.dto.booking.BookingDto;
import md.ceiti.spring.entity.dto.city.CityDto;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.security.CustomUserDetails;
import md.ceiti.spring.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public BookingContainerDto findAll() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public BookingDto findById(@PathVariable Integer id){
        return bookingService.findById(id);
    }

    @GetMapping("/tours/{id}")
    public BookingContainerDto findByTourId(@PathVariable Integer id){
        return bookingService.findByTourId(id);
    }
}
