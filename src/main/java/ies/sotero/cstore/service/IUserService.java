package ies.sotero.cstore.service;

import java.util.Optional;

import ies.sotero.cstore.model.User;

public interface IUserService {
	public Optional<User> findbyId(Integer id);
	
	public User save(User user);
	
	public Optional<User> finByEmail(String email);
}
