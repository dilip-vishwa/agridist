//package com.callforcode.agridist.services;
//
//import com.callforcode.agridist.entities.User1;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Component
//public class FakeUserService {
//    private static List<User1> list = new ArrayList<>();
//
//    static {
//        list.add(new User1(12, "Prakash", 26));
//        list.add(new User1(12, "Pankaj", 32));
//        list.add(new User1(12, "Piyush", 45));
//    }
//
//    public List<User1> getAllUsers() {
//        return list;
//    }
//
//    public Optional<User1> getUserById(int id) {
//        User1 user = null;
//        try {
//            return Optional.of(list.stream().filter(e -> e.getId() == id).findFirst().get());
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return Optional.of(user);
//    }
//
//    public User1 addUser(User1 u) {
//        list.add(u);
//        return u;
//    }
//
//    public User1 updateUser(User1 u, int id) {
//        list.stream().map(user-> {
//            if(user.getId() == id) {
//                user.setName(u.getName());
//                user.setAge(u.getAge());
//            }
//            return user;
//        }).collect(Collectors.toList());
//        return u;
//    }
//
//    public void deleteUser(int id) {
//        list = list.stream().filter(user->user.getId() != id).collect(Collectors.toList());
//    }
//
//}
