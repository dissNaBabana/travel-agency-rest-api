package md.ceiti.spring.service;

import md.ceiti.spring.entity.City;
import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.dto.city.CityDto;
import md.ceiti.spring.entity.dto.country.CountryContainerDto;
import md.ceiti.spring.entity.dto.country.CountryDto;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.entity.dto.request.CityRequest;
import md.ceiti.spring.entity.dto.request.HotelRequest;
import md.ceiti.spring.repository.CityRepository;
import md.ceiti.spring.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, CityRepository cityRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
    }

    public HotelContainerDto findAll(){
        List<HotelDto> hotels = hotelRepository.findAll().stream()
                .map(Hotel::toDto)
                .collect(Collectors.toList());
        return new HotelContainerDto(hotels);
    }

    public HotelDto findById(Integer id){
        return hotelRepository.findById(id)
                .map(Hotel::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Hotel with id" + id + "not founded"));
    }

    public HotelDto save(HotelRequest request){
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City with id " + request.getCityId() + " not found"));;
        Hotel hotel = request.toEntity(city);
        return hotelRepository.save(hotel).toDto();
    }

    public HotelDto update(Integer id, HotelRequest request){
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City with id " + request.getCityId() + " not found"));;
        return hotelRepository.findById(id)
                .map(existingHotel -> {
                    Hotel updatedHotel = request.toEntity(existingHotel.getHotelId(), city);
                    return hotelRepository.save(updatedHotel).toDto();
                })
                .orElseThrow(() -> new IllegalArgumentException("City with id" + id + "not founded"));
    }
}
