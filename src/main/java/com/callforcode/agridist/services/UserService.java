package com.callforcode.agridist.services;

import com.callforcode.agridist.dao.UserRepository;
import com.callforcode.agridist.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> user = (List<User>) this.userRepository.findAll();
        return user;
    }

    public Optional<User> getUserById(int id) {
        Optional<User> user = null;
        try {
            user = this.userRepository.findById(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User addUser(User u) {
        this.userRepository.save(u);
        return u;
    }

    public User updateUser(User u, int id) {
        u.setId(id);
        this.userRepository.save(u);
        return u;
    }

    public void deleteUser(int id) {
        this.userRepository.deleteById(id);
    }

}
