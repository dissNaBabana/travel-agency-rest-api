package md.ceiti.spring.entity.dto.hotel;

import md.ceiti.spring.entity.dto.tour.TourDto;

import java.util.List;

public class HotelContainerDto {
    private final List<HotelDto> hotels;

    public HotelContainerDto(List<HotelDto> hotels) {
        this.hotels = hotels;
    }

    public List<HotelDto> getHotels() {
        return hotels;
    }
}
