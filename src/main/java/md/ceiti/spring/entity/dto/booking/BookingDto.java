package md.ceiti.spring.entity.dto.booking;

import md.ceiti.spring.entity.BookingStatus;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDto {
    private Integer bookingId;
    private Integer userId;
    private Integer tourId;
    private LocalDateTime bookingDate;
    private Integer peopleCount;
    private BigDecimal totalPrice;
    private BookingStatus status;

    public BookingDto() {
    }

    public BookingDto(Integer bookingId, Integer userId, Integer tourId, LocalDateTime bookingDate, Integer peopleCount, BigDecimal totalPrice, BookingStatus status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.tourId = tourId;
        this.bookingDate = bookingDate;
        this.peopleCount = peopleCount;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
