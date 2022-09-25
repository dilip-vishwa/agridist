//package com.callforcode.agridist.services;
//
//import com.callforcode.agridist.dao.UserRepository;
//import com.callforcode.agridist.entities.User1;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    public List<User1> getAllUsers() {
//        List<User1> user = (List<User1>) this.userRepository.findAll();
//        return user;
//    }
//
//    public Optional<User1> getUserById(int id) {
//        Optional<User1> user = null;
//        try {
//            user = this.userRepository.findById(id);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    public User1 addUser(User1 u) {
//        this.userRepository.save(u);
//        return u;
//    }
//
//    public User1 updateUser(User1 u, int id) {
//        u.setId(id);
//        this.userRepository.save(u);
//        return u;
//    }
//
//    public void deleteUser(int id) {
//        this.userRepository.deleteById(id);
//    }
//
//}
