package md.ceiti.spring.repository;

import md.ceiti.spring.entity.Tour;
import md.ceiti.spring.entity.User;
import md.ceiti.spring.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole userRole);
    Optional<User> findByEmail(String email);

}
