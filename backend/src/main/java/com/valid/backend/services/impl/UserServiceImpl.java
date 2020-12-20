package com.valid.backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valid.backend.model.User;
import com.valid.backend.repositories.UserRepository;
import com.valid.backend.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;

	public Optional<User> findById(long idUser) {
		return userRepository.findById(idUser);
	}
	
	public boolean deleteUser(long idUser) {
		
		userRepository.deleteById(idUser);
		if(!userRepository.findById(idUser).isPresent() ) {
			return true;
		}
		return false;
	}

	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public User create(User newUser) {
		return userRepository.save(newUser);
	}


	@Override
	public void processUsers(List<Long> userIds) {
		if(!userIds.isEmpty()) {
			for (Long i: userIds) {
				User userFound = userRepository.findById(i).get();
				if(!userFound.isProcesado()) {
					userFound.setProcesado(true);
					userRepository.save(userFound);
				}
			}
		}
		
	}
	
}
