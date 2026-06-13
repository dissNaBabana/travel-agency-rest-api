package md.ceiti.spring.controller.forPrivate;

import md.ceiti.spring.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/tours/{tourId}/image")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<Map<String, String>> uploadTourImage(
            @PathVariable Integer tourId,
            @RequestParam("image") MultipartFile file) throws IOException {

        String url = imageService.uploadTourImage(tourId, file);
        return ResponseEntity.ok(Map.of("image_url", url));
    }

    @DeleteMapping("/tours/{tourId}/image")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<Void> deleteTourImage(@PathVariable Integer tourId) {
        imageService.deleteTourImage(tourId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hotels/{hotelId}/images")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<Map<String, String>> uploadHotelImage(
            @PathVariable Integer hotelId,
            @RequestParam("image") MultipartFile file) throws IOException {

        String url = imageService.uploadHotelImage(hotelId, file);
        return ResponseEntity.ok(Map.of("image_url", url));
    }

    @DeleteMapping("/hotels/images/{imageId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<Void> deleteHotelImage(@PathVariable Integer imageId) {
        imageService.deleteHotelImage(imageId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tours/{tourId}/image")
    public ResponseEntity<Map<String, String>> getTourImage(@PathVariable Integer tourId) {
        String url = imageService.getTourImage(tourId);
        if (url == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("image_url", url));
    }

    @GetMapping("/hotels/{hotelId}/images")
    public ResponseEntity<List<String>> getHotelImages(@PathVariable Integer hotelId) {
        return ResponseEntity.ok(imageService.getHotelImages(hotelId));
    }
}