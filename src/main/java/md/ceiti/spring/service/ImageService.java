package md.ceiti.spring.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.HotelImage;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.TourImage;
import md.ceiti.spring.repository.HotelImageRepository;
import md.ceiti.spring.repository.HotelRepository;
import md.ceiti.spring.repository.TourImageRepository;
import md.ceiti.spring.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ImageService {

    private final Cloudinary cloudinary;
    private final TourImageRepository tourImageRepository;
    private final HotelImageRepository hotelImageRepository;
    private final TourRepository tourRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public ImageService(Cloudinary cloudinary, TourImageRepository tourImageRepository, HotelImageRepository hotelImageRepository, TourRepository tourRepository, HotelRepository hotelRepository) {
        this.cloudinary = cloudinary;
        this.tourImageRepository = tourImageRepository;
        this.hotelImageRepository = hotelImageRepository;
        this.tourRepository = tourRepository;
        this.hotelRepository = hotelRepository;
    }

    public String uploadTourImage(Integer tourId, MultipartFile file) throws IOException {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        tourImageRepository.findByTour_TourId(tourId).ifPresent(old -> {
            deleteFromCloudinary(old.getImageUrl());
            tourImageRepository.delete(old);
        });

        String url = uploadToCloudinary(file, "tours");

        TourImage image = new TourImage();
        image.setTour(tour);
        image.setImageUrl(url);
        tourImageRepository.save(image);

        return url;
    }

    public void deleteTourImage(Integer tourId) {
        tourImageRepository.findByTour_TourId(tourId).ifPresent(old -> {
            deleteFromCloudinary(old.getImageUrl());
            tourImageRepository.delete(old);
        });
    }


    public String uploadHotelImage(Integer hotelId, MultipartFile file) throws IOException {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        String url = uploadToCloudinary(file, "hotels");

        HotelImage image = new HotelImage();
        image.setHotel(hotel);
        image.setImageUrl(url);
        hotelImageRepository.save(image);

        return url;
    }

    public void deleteHotelImage(Integer imageId) {
        hotelImageRepository.findById(imageId).ifPresent(img -> {
            deleteFromCloudinary(img.getImageUrl());
            hotelImageRepository.delete(img);
        });
    }


    private String uploadToCloudinary(MultipartFile file, String folder) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", "travel_agency/" + folder)
        );
        return uploadResult.get("secure_url").toString();
    }

    private void deleteFromCloudinary(String imageUrl) {
        try {
            String[] parts = imageUrl.split("/");
            String folder = parts[parts.length - 2];
            String filename = parts[parts.length - 1].split("\\.")[0];
            String publicId = "travel_agency/" + folder + "/" + filename;
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
        }
    }

    public String getTourImage(Integer tourId) {
        return tourImageRepository.findByTour_TourId(tourId)
                .map(TourImage::getImageUrl)
                .orElse(null);
    }

    public List<String> getHotelImages(Integer hotelId) {
        return hotelImageRepository.findAllByHotel_HotelId(hotelId)
                .stream()
                .map(HotelImage::getImageUrl)
                .toList();
    }
}