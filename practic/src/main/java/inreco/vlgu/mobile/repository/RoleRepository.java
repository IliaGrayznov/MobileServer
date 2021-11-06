package inreco.vlgu.mobile.repository;

import inreco.vlgu.mobile.model.ERole;
import inreco.vlgu.mobile.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
