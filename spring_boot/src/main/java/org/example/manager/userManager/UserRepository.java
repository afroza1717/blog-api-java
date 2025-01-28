package org.example.manager.userManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.model.db.User;

import java.util.List;
import java.util.Optional;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByUsernameAndPassword(String userName, String password);
}
