package md.ceiti.spring.entity.dto.tour;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TourCreateDto {
    private String title;
    private String description;
    private Long countryId;
    private Long hotelId;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxPeople;
    private Boolean isHot;

    public TourCreateDto(String title, String description, Long countryId, Long hotelId, BigDecimal price, LocalDate startDate, LocalDate endDate, Integer maxPeople, Boolean isHot) {
        this.title = title;
        this.description = description;
        this.countryId = countryId;
        this.hotelId = hotelId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxPeople = maxPeople;
        this.isHot = isHot;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getHotelId() {
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

    public Boolean getHot() {
        return isHot;
    }
}