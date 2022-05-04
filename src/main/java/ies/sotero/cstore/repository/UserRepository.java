package ies.sotero.cstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ies.sotero.cstore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);

}
