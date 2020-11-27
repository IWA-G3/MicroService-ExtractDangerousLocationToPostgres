package polytech.group3.iwa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.group3.iwa.models.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {}