package md.ceiti.spring.entity.dto.tour;

import jakarta.persistence.*;
import md.ceiti.spring.entity.Country;
import md.ceiti.spring.entity.Hotel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TourDto {
         private Long tourId;
        private String title;
        private String description;
        private Country country;
        private Hotel hotelId;
        private BigDecimal price;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer maxPeople;
        private Integer availablePlaces;
        private Boolean isHot;
        private LocalDate createdAt;

    public TourDto(Long tourId, String title, String description, Country country, Hotel hotelId, BigDecimal price, LocalDate startDate, LocalDate endDate, Integer maxPeople, Integer availablePlaces, Boolean isHot, LocalDate createdAt) {
        this.tourId = tourId;
        this.title = title;
        this.description = description;
        this.country = country;
        this.hotelId = hotelId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPeople = maxPeople;
        this.availablePlaces = availablePlaces;
        this.isHot = isHot;
        this.createdAt = createdAt;
    }

    public Long getTourId() {
        return tourId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Country getCountry() {
        return country;
    }

    public Hotel getHotelId() {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
