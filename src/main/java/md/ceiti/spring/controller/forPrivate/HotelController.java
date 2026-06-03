package md.ceiti.spring.controller.forPrivate;

import md.ceiti.spring.entity.dto.hotel.HotelContainerDto;
import md.ceiti.spring.entity.dto.hotel.HotelDto;
import md.ceiti.spring.entity.dto.request.HotelRequest;
import md.ceiti.spring.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping()
    public HotelContainerDto findAll(){
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    public HotelDto findById(@PathVariable Integer id){

        return hotelService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping()
    public HotelDto save( @RequestBody HotelRequest request){
        return hotelService.save(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public HotelDto update(@PathVariable Integer id, @RequestBody HotelRequest request){
        return hotelService.update(id, request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        hotelService.delete(id);
    }

}
