package md.ceiti.spring.entity.dto.request;

import md.ceiti.spring.entity.BookingStatus;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingRequest {
    private Integer bookingId;
    private Integer tourId;
    private Integer peopleCount;

    public BookingRequest() {
    }

    public BookingRequest(Integer bookingId, Integer tourId, Integer peopleCount) {
        this.bookingId = bookingId;
        this.tourId = tourId;
        this.peopleCount = peopleCount;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }
}
