package md.ceiti.spring.entity;

import jakarta.persistence.*;
import md.ceiti.spring.entity.dto.tour.TourDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Integer tourId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "max_people")
    private Integer maxPeople;

    @Column(name = "available_places")
    private Integer availablePlaces;

    @Column(name = "is_hot")
    private Boolean isHot;


    @Column(name = "created_at")
    private LocalDate createdAt;


    public Tour() {
    }

    public Tour(Integer tourId, String title, String description, Country country, Hotel hotel, BigDecimal price, LocalDate startDate, LocalDate endDate, Integer maxPeople, Integer availablePlaces, Boolean isHot, LocalDate createdAt) {
        this.tourId = tourId;
        this.title = title;
        this.description = description;
        this.country = country;
        this.hotel = hotel;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPeople = maxPeople;
        this.availablePlaces = availablePlaces;
        this.isHot = isHot;
        this.createdAt = LocalDate.now();
    }

    public Tour(String title, String description, Country country, Hotel hotel, BigDecimal price, LocalDate startDate, LocalDate endDate, Integer maxPeople, Integer availablePlaces, Boolean isHot) {
        this.title = title;
        this.description = description;
        this.country = country;
        this.hotel = hotel;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPeople = maxPeople;
        this.availablePlaces = availablePlaces;
        this.isHot = isHot;
        this.createdAt = LocalDate.now();

    }

    public Tour(Integer tourId, String title, String description, Country country, Hotel hotel, BigDecimal price, LocalDate startDate, LocalDate endDate, Integer maxPeople, Integer availablePlaces, Boolean isHot) {
        this.tourId = tourId;
        this.title = title;
        this.description = description;
        this.country = country;
        this.hotel = hotel;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPeople = maxPeople;
        this.availablePlaces = availablePlaces;
        this.isHot = isHot;
        this.createdAt = LocalDate.now();
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Hotel getHotelId() {
        return hotel;
    }

    public void setHotelId(Hotel hotelId) {
        this.hotel = hotelId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(Integer availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public TourDto toDto(){
        return new TourDto(
                tourId,
                title,
                description,
                country.getCountryId(),
                hotel.getHotelId(),
                price,
                startDate,
                endDate,
                maxPeople,
                availablePlaces,
                isHot,
                createdAt );
    }

}
