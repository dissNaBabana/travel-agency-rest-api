package md.ceiti.spring.repository;

import md.ceiti.spring.entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourImageRepository extends JpaRepository<TourImage, Integer> {
    Optional<TourImage> findByTour_TourId(Integer tourId);
    void deleteByTour_TourId(Integer tourId);
}