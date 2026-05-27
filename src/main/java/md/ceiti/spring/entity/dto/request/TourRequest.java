package md.ceiti.spring.entity.dto.request;

import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.Tour;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TourRequest {
    private String title;
    private String description;
    private Integer countryId;
    private Integer hotelId;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxPeople;
    private Integer availablePlaces;
    private Boolean isHot;

    public TourRequest(String title, String description, Integer countryId, Integer hotelId, BigDecimal price, LocalDate startDate, LocalDate endDate, Integer maxPeople, Integer availablePlaces, Boolean isHot) {
        this.title = title;
        this.description = description;
        this.countryId = countryId;
        this.hotelId = hotelId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPeople = maxPeople;
        this.availablePlaces = availablePlaces;
        this.isHot = isHot;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public Integer getAvailablePlaces() {
        return availablePlaces;
    }

    public Boolean getHot() {
        return isHot;
    }

    public Tour toEntity(Country country, Hotel hotel){
        return new Tour(
                title,
                description,
                country,
                hotel,
                price,
                startDate,
                endDate,
                maxPeople,
                availablePlaces,
                isHot);

    }

    public Tour toEntity(Integer id,Country country, Hotel hotel){
        return new Tour(
                id,
                title,
                description,
                country,
                hotel,
                price,
                startDate,
                endDate,
                maxPeople,
                availablePlaces,
                isHot);

    }
}