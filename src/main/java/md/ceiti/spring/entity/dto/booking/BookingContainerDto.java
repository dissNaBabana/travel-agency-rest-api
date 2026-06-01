package md.ceiti.spring.entity.dto.booking;

import md.ceiti.spring.entity.dto.city.CityDto;

import java.util.List;

public class BookingContainerDto {
    private final List<BookingDto> bookings;

    public BookingContainerDto(List<BookingDto> bookings) {
        this.bookings = bookings;
    }

    public List<BookingDto> getBookings() {
        return bookings;
    }
}
