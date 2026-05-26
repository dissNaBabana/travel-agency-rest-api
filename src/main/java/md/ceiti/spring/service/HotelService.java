package md.ceiti.spring.service;

import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.dto.country.CountryContainerDto;
import md.ceiti.spring.entity.dto.country.CountryDto;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelContainerDto findAll(){
        List<HotelDto> hotels = hotelRepository.findAll().stream()
                .map(Hotel::toDto)
                .collect(Collectors.toList());
        return new HotelContainerDto(hotels);
    }
}
