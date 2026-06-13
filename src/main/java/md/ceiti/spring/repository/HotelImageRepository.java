package md.ceiti.spring.repository;

import md.ceiti.spring.entity.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelImageRepository extends JpaRepository<HotelImage, Integer> {
    List<HotelImage> findAllByHotel_HotelId(Integer hotelId);
}