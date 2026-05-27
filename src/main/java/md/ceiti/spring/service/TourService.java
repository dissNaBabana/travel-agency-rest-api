package md.ceiti.spring.service;

import md.ceiti.spring.entity.City;
import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.entity.dto.tour.TourContainerDto;
import md.ceiti.spring.entity.dto.tour.TourDto;
import md.ceiti.spring.repository.CountryRepository;
import md.ceiti.spring.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {
    private final TourRepository tourRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public TourService(TourRepository tourRepository, CountryRepository countryRepository) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
    }

    public TourContainerDto findAll(){
        List<TourDto> tours = tourRepository.findAll().stream()
                .map(Tour::toDto)
                .collect(Collectors.toList());
        return new TourContainerDto(tours);
    }

    public TourDto findById(Integer id){
        return tourRepository.findById(id)
                .map(Tour::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Tour with id" + id + "not founded"));
    }

    public TourContainerDto findAllHot(){
        List<TourDto> tours = tourRepository.findByIsHot(true).stream()
                .map(Tour::toDto)
                .collect(Collectors.toList());
        return new TourContainerDto(tours);
    }

    public TourContainerDto findAllNew(){
        LocalDate now = LocalDate.now();

        LocalDate start = now.minusDays(2);
        LocalDate end = now.plusDays(1);

        List<TourDto> tours = tourRepository.findByCreatedAtBetween(start, end).stream()
                .map(Tour::toDto)
                .collect(Collectors.toList());
        return new TourContainerDto(tours);
    }

    public TourContainerDto filter(Integer countryId, BigDecimal minPrise, BigDecimal maxPrise){
        if(countryId != null){
            Country country = countryRepository.findById(countryId)
                    .orElseThrow(() -> new IllegalArgumentException("City with id " + countryId + " not found"));;

            List<TourDto> tours = tourRepository.findByCountryAndPriceBetween(country, minPrise, maxPrise).stream()
                    .map(Tour::toDto)
                    .collect(Collectors.toList());

            return new TourContainerDto(tours);
        }
        else {
            List<TourDto> tours = tourRepository.findByPriceBetween(minPrise, maxPrise).stream()
                    .map(Tour::toDto)
                    .collect(Collectors.toList());

            return new TourContainerDto(tours);        }
    }
}
