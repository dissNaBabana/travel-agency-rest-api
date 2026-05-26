package md.ceiti.spring.entity.dto.hotel;

import md.ceiti.spring.entity.City;

public class HotelDto {
    private Integer hotelId;
    private String hotelName;
    private City city;
    private Integer stars;
    private String address;
    private String hotelDescription;
    private String phone;

    public HotelDto(Integer hotelId, String hotelName, City city, Integer stars, String address, String hotelDescription, String phone) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.city = city;
        this.stars = stars;
        this.address = address;
        this.hotelDescription = hotelDescription;
        this.phone = phone;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public City getCity() {
        return city;
    }

    public Integer getStars() {
        return stars;
    }

    public String getAddress() {
        return address;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public String getPhone() {
        return phone;
    }
}
