package md.ceiti.spring.repository;

import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findByIsHot(boolean isHot);
    List<Tour> findByCreatedAtBetween(LocalDate start, LocalDate end);
    List<Tour> findByCountryAndPriceBetween(Country country, BigDecimal minPrice, BigDecimal maxPrice);
    List<Tour> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
