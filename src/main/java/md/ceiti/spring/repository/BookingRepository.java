package md.ceiti.spring.repository;

import md.ceiti.spring.entity.Booking;
import md.ceiti.spring.entity.City;
import md.ceiti.spring.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, Integer> {
    List<Booking> findByTour(Tour tour);

}
