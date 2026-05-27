package md.ceiti.spring.service;

import md.ceiti.spring.entity.City;
import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.Tour;

import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.entity.dto.request.HotelRequest;
import md.ceiti.spring.entity.dto.request.TourRequest;
import md.ceiti.spring.entity.dto.tour.TourContainerDto;
import md.ceiti.spring.entity.dto.tour.TourDto;
import md.ceiti.spring.repository.CountryRepository;
import md.ceiti.spring.repository.HotelRepository;
import md.ceiti.spring.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {
    private final TourRepository tourRepository;
    private final CountryRepository countryRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public TourService(TourRepository tourRepository, CountryRepository countryRepository, HotelRepository hotelRepository) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
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

            return new TourContainerDto(tours); }
    }

    public TourDto save(TourRequest request){
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country with id " + request.getCountryId() + " not found"));;
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel with id " + request.getHotelId() + " not found"));;

        Tour tour = request.toEntity(country, hotel);
        return tourRepository.save(tour).toDto();
    }


    public TourDto update(Integer id, TourRequest request){
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country with id " + request.getCountryId() + " not found"));;
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel with id " + request.getHotelId() + " not found"));;

         return tourRepository.findById(id)
                .map(existingTour -> {
                    Tour updatedTour = request.toEntity(existingTour.getTourId(), country, hotel);
                    return tourRepository.save(updatedTour).toDto();
                })
                .orElseThrow(() -> new IllegalArgumentException("Tour with id" + id + "not founded"));
    }

    public void delete(int id) {
        tourRepository.deleteById(id);
    }

}
