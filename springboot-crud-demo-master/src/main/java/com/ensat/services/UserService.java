package com.ensat.services;

import com.ensat.entities.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);
}
