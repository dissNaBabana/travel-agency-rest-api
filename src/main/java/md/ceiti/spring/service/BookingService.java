package md.ceiti.spring.service;

import md.ceiti.spring.entity.Booking;
import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.dto.booking.BookingContainerDto;
import md.ceiti.spring.entity.dto.booking.BookingDto;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.repository.BookingRepository;
import md.ceiti.spring.repository.TourRepository;
import md.ceiti.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, TourRepository tourRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
    }

    public BookingContainerDto findAll(){
        List<BookingDto> bookings = bookingRepository.findAll().stream()
                .map(Booking::toDto)
                .collect(Collectors.toList());
        return new BookingContainerDto(bookings);
    }

    public BookingDto findById(Integer id){
        return bookingRepository.findById(id)
                .map(Booking::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Booking with id" + id + "not founded"));
    }

    public BookingContainerDto findByTourId(Integer id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tour with id " + id + " not found"
                ));

        List<BookingDto> bookings = bookingRepository.findByTour(tour)
                .stream()
                .map(Booking::toDto)
                .toList();

        return new BookingContainerDto(bookings);
    }
}
