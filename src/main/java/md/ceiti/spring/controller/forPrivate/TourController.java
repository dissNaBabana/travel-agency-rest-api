package md.ceiti.spring.controller.forPrivate;

import md.ceiti.spring.entity.dto.request.TourRequest;
import md.ceiti.spring.entity.dto.tour.TourContainerDto;
import md.ceiti.spring.entity.dto.tour.TourDto;
import md.ceiti.spring.service.TourService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/tours")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping()
    public TourContainerDto findAll() {
        return tourService.findAll();

    }

    @GetMapping("/{id}")
    public TourDto findById(@PathVariable Integer id) {
        return tourService.findById(id);
    }

    @GetMapping("/filter")
    public TourContainerDto filter(
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {

        return tourService.filter(countryId, minPrice, maxPrice);

    }

    @GetMapping("/is_hot")
    public TourContainerDto getHotTours() {
        return tourService.findAllHot();
    }

    @GetMapping("/new")
    public TourContainerDto getNewTours() {
        return tourService.findAllNew();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping()
    public TourDto save(@RequestBody TourRequest request){
        return tourService.save(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public TourDto update(@PathVariable Integer id, @RequestBody TourRequest request){
        return tourService.update(id, request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        tourService.delete(id);
    }

}
