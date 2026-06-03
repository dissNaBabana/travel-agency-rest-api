package md.ceiti.spring.controller.forPrivate;

import md.ceiti.spring.entity.dto.booking.BookingContainerDto;
import md.ceiti.spring.entity.dto.request.UserRequest;
import md.ceiti.spring.entity.dto.tour.TourContainerDto;
import md.ceiti.spring.entity.dto.user.UserDto;
import md.ceiti.spring.security.CustomUserDetails;
import md.ceiti.spring.service.BookingService;
import md.ceiti.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class UserController {
    private final UserService userService;
    private final BookingService bookingService;

    @Autowired
    public UserController(UserService userService, BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/me")
    public UserDto findById(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.findById(userDetails.getUser().getUserId());
    }

    @PutMapping("/me")
    public UserDto update(@AuthenticationPrincipal CustomUserDetails userDetails,
                            @RequestBody UserRequest request) {
        return userService.update(userDetails.getUser().getUserId(), request);
    }

    @DeleteMapping("/me")
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.delete(userDetails.getUser().getUserId());
    }

    @GetMapping("/me/favorites")
    public TourContainerDto findAllMyFavorite(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.findAllMyFavorite(userDetails.getUser().getUserId());
    }


    @PostMapping("/me/favorites/{tourId}")
    public void markAsFavorite(@AuthenticationPrincipal CustomUserDetails userDetails,
                       @PathVariable Integer tourId) {
        userService.markAsFavorite(userDetails.getUser(), tourId);
    }

    @DeleteMapping("/me/favorites/{tourId}")
    public void deleteFavorite(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @PathVariable Integer tourId) {
        userService.deleteFavorite(userDetails.getUser(), tourId);
    }

    @GetMapping("/me/bookings")
    public BookingContainerDto findBookingsByClientId(@AuthenticationPrincipal CustomUserDetails userDetails){
        return bookingService.findBookingsByClientId(userDetails.getUser().getUserId());
    }

}
