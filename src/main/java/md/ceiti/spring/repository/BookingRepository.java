package md.ceiti.spring.repository;

import md.ceiti.spring.entity.Booking;
import md.ceiti.spring.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, Integer> {
}
