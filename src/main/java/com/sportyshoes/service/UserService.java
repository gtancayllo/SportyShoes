package com.sportyshoes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.entity.User;
import com.sportyshoes.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(m-> users.add(m));
		return users;
	}
	
	public List<User> getSignedUpUsers(){
		List<User> users = new ArrayList<User>();
		userRepository.findUserSignedUp().forEach(m-> users.add(m));
		return users;
	}
	
	public User findUserLogged(User user){
		List<User> users = userRepository.findUserByUserNamePassword(user.getUsername(), user.getPassword());
		if(users!= null && users.size()>0) {
			return users.get(0);
		}
		return null;	  
	}
	
	
    public void saveUser(User user) {
        User newUser = new User();
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        // encrypt the password using spring security
        newUser.setPassword(user.getPassword());
        newUser.setRole("User");
        newUser.setSignedUp(true);
        userRepository.save(user);
    }

    
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
