package md.ceiti.spring.entity.dto.request;

import md.ceiti.spring.entity.Booking;
import md.ceiti.spring.entity.BookingStatus;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingRequest {
    private Integer bookingId;
    private Integer tourId;
    private LocalDateTime bookingDate;
    private Integer peopleCount;

    public BookingRequest() {
    }

    public BookingRequest(Integer bookingId, Integer tourId, LocalDateTime bookingDate, Integer peopleCount) {
        this.bookingId = bookingId;
        this.tourId = tourId;
        this.bookingDate = bookingDate;
        this.peopleCount = peopleCount;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public Booking toEntity(User user, Tour tour, BigDecimal totalPrice){
        return new Booking(
                user,
                tour,
                LocalDateTime.now(),
                peopleCount,
                totalPrice,
                BookingStatus.PENDING);

    }
}
