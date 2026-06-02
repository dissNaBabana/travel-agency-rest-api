package md.ceiti.spring.service;

import md.ceiti.spring.entity.*;
import md.ceiti.spring.entity.dto.booking.BookingContainerDto;
import md.ceiti.spring.entity.dto.booking.BookingDto;
import md.ceiti.spring.entity.dto.request.BookingRequest;
import md.ceiti.spring.repository.BookingRepository;
import md.ceiti.spring.repository.TourRepository;
import md.ceiti.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                .orElseThrow(() -> new IllegalArgumentException("Tour with id " + id + " not found"));
        List<BookingDto> bookings = bookingRepository.findByTour(tour)
                .stream()
                .map(Booking::toDto)
                .toList();
        return new BookingContainerDto(bookings);
    }

    public BookingDto save(User user, BookingRequest request) {
        Tour tour = tourRepository.findById(request.getTourId())
                .orElseThrow(() -> new IllegalArgumentException("Tour with id " + request.getTourId() + " not found"));
        if (tour.getAvailablePlaces() < request.getPeopleCount()) {
            throw new IllegalArgumentException("Not enough available places");
        }
        tour.setAvailablePlaces(
                tour.getAvailablePlaces() - request.getPeopleCount());

        BigDecimal totalPrice = tour.getPrice()
                .multiply(BigDecimal.valueOf(request.getPeopleCount()));
        Booking booking = request.toEntity(user, tour, totalPrice);

        Booking saved = bookingRepository.save(booking);
        return saved.toDto();
    }

    public BookingDto putCancelStatus(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking with id " + id + " not found"));

        if (booking.getStatus() != BookingStatus.CANCELLED && booking.getStatus() != BookingStatus.PAID) {
            booking.setStatus(BookingStatus.CANCELLED);

            Tour tour = tourRepository.findById(booking.getTour().getTourId())
                    .orElseThrow(() -> new IllegalArgumentException("Tour not found"));

            tour.setAvailablePlaces(tour.getAvailablePlaces() + booking.getPeopleCount());
            tourRepository.save(tour);

            return bookingRepository.save(booking).toDto();
        }

        return booking.toDto();
    }

    public BookingDto putPaidStatus(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking with id " + id + " not found"));

        if (booking.getStatus() != BookingStatus.CANCELLED ) {
            booking.setStatus(BookingStatus.PAID);
            return bookingRepository.save(booking).toDto();
        }
        return booking.toDto();
    }
}
