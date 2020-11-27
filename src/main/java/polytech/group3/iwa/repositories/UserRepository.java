package polytech.group3.iwa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import polytech.group3.iwa.models.User;


public interface UserRepository extends JpaRepository<User, String> {}
