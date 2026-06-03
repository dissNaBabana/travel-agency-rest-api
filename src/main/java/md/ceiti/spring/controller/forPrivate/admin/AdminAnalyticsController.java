package md.ceiti.spring.controller.forPrivate.admin;

import md.ceiti.spring.entity.BookingStatus;
import md.ceiti.spring.entity.dto.booking.BookingContainerDto;
import md.ceiti.spring.entity.dto.tour.TourContainerDto;
import md.ceiti.spring.entity.dto.user.UserContainerDto;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.entity.dto.user.UserWithoutPasswordContainerDto;
import md.ceiti.spring.entity.dto.user.UserWithoutPasswordDto;
import md.ceiti.spring.security.CustomUserDetails;
import md.ceiti.spring.service.BookingService;
import md.ceiti.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminAnalyticsController {
    private final UserService userService;
    private final BookingService bookingService;

    @Autowired
    public AdminAnalyticsController(UserService userService, BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/stats/bookings")
    public BookingContainerDto findAllBookings() {

        return bookingService.findAll();
    }

    @GetMapping("/clients")
    public UserWithoutPasswordContainerDto findAllClients() {

        return userService.findAllClients();
    }

    @GetMapping("/clients/{id}")
    public UserWithoutPasswordDto findClient(@PathVariable Integer id) {

        return userService.findClient(id);
    }

    @GetMapping("/clients/{id}/bookings")
    public BookingContainerDto findBookingsByClientId(@PathVariable Integer id){
        return bookingService.findBookingsByClientId(id);
    }

    @GetMapping("/stats/bookings/filter")
    public BookingContainerDto filter(@RequestParam()BookingStatus status){

        return bookingService.filter(status);

    }

}
