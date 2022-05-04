package ies.sotero.cstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.sotero.cstore.model.User;
import ies.sotero.cstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findbyId(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> finByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}