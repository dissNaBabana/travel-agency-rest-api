package md.ceiti.spring.repository;

import jakarta.transaction.Transactional;
import md.ceiti.spring.entity.Favorite;
import md.ceiti.spring.entity.Hotel;
import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query("""
            SELECT f.tour
            FROM Favorite f
            WHERE f.user.userId = :userId
            """)
    List<Tour> findFavoriteToursByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.user = :user AND f.tour.id = :tourId")
    void deleteByUserAndTourId(User user, Integer tourId);
}
