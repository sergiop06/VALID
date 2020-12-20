package com.valid.backend.services;

import java.util.List;

import com.valid.backend.model.User;

public interface UserService {
	public User create(User newUser);
	public boolean deleteUser(long idUser);
	public List<User> findAllUsers();
	public void processUsers(List<Long> userIds);
}
