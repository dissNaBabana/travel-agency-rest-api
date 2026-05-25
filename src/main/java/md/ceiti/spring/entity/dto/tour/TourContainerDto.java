package md.ceiti.spring.entity.dto.tour;

import java.util.List;

public class TourContainerDto {
    private final List<TourDto> tours;

    public TourContainerDto(List<TourDto> tours) {
        this.tours = tours;
    }

    public List<TourDto> getTours() {
        return tours;
    }
}
