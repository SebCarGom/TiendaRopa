package ies.sotero.cstore.service;

import java.util.Optional;

import ies.sotero.cstore.model.User;

public interface UserService {
	Optional<User> findbyId(Integer id);
	
	User save(User user);
	
	Optional<User> finByEmail(String email);
}
