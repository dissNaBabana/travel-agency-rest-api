package md.ceiti.spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "address")
    private String address;

    @Column(name = "hotel_description")
    private String hotelDescription;

    @Column(name = "phone")
    private String phone;

    public Hotel() {
    }

    public Hotel(Integer hotelId, String hotelName, City city, Integer stars, String address, String hotelDescription, String phone) {
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

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
