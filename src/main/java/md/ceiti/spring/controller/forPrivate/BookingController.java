package md.ceiti.spring.controller.forPrivate;

import md.ceiti.spring.entity.dto.booking.BookingContainerDto;
import md.ceiti.spring.entity.dto.booking.BookingDto;
import md.ceiti.spring.entity.dto.request.BookingRequest;
import md.ceiti.spring.security.CustomUserDetails;
import md.ceiti.spring.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public BookingDto findById(@PathVariable Integer id){
        return bookingService.findById(id);
    }

    @GetMapping("/tours/{id}")
    public BookingContainerDto findByTourId(@PathVariable Integer id){
        return bookingService.findByTourId(id);
    }

    @PostMapping
    public BookingDto save(@AuthenticationPrincipal CustomUserDetails userDetails,
                          @RequestBody BookingRequest request) {
        return bookingService.save(userDetails.getUser(), request);
    }

    @PatchMapping("/{id}/cancel")
    public BookingDto putCancelStatus(@PathVariable Integer id) {
        return bookingService.putCancelStatus(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PatchMapping("/{id}/paid")
    public BookingDto putPaidStatus(@PathVariable Integer id) {
        return bookingService.putPaidStatus(id);
    }
}
