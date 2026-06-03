package md.ceiti.spring.controller.forPrivate.admin;
import md.ceiti.spring.entity.BookingStatus;
import md.ceiti.spring.entity.dto.booking.BookingContainerDto;
import md.ceiti.spring.entity.dto.user.UserWithoutPasswordContainerDto;
import md.ceiti.spring.entity.dto.user.UserWithoutPasswordDto;
import md.ceiti.spring.service.BookingService;
import md.ceiti.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
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
