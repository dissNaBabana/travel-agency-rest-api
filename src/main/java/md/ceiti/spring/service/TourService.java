package md.ceiti.spring.service;

import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.entity.dto.tour.TourContainerDto;
import md.ceiti.spring.entity.dto.tour.TourDto;
import md.ceiti.spring.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {
    private final TourRepository tourRepository;

    @Autowired
    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
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
}
