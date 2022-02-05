package com.demiglace.spring.springmvcorm.user.entity.user.dao;

import java.util.List;

import com.demiglace.spring.springmvcorm.user.entity.User;

public interface UserDao {
	int create(User user);
	List<User> findUsers();
	User findUser(Integer id);
}
