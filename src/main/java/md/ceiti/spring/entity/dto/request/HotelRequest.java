package md.ceiti.spring.entity.dto.request;

import md.ceiti.spring.entity.City;
import md.ceiti.spring.entity.Hotel;

public class HotelRequest {
    private String hotelName;
    private Integer cityId;
    private Integer stars;
    private String address;
    private String hotelDescription;
    private String phone;

    public HotelRequest(String hotelName, Integer cityId, Integer stars, String address, String hotelDescription, String phone) {
        this.hotelName = hotelName;
        this.cityId = cityId;
        this.stars = stars;
        this.address = address;
        this.hotelDescription = hotelDescription;
        this.phone = phone;
    }


    public String getHotelName() {
        return hotelName;
    }

    public Integer getCityId() {
        return cityId;
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

    public Hotel toEntity(City city){
        return new Hotel(
                hotelName,
                city,
                stars,
                address,
                hotelDescription,
                phone);

    }

    public Hotel toEntity(Integer id, City city){
        return new Hotel(
                id,
                hotelName,
                city,
                stars,
                address,
                hotelDescription,
                phone);

    }
}
