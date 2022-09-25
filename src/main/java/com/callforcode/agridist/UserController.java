//package com.callforcode.agridist;
//import com.callforcode.agridist.entities.User1;
//import com.callforcode.agridist.services.FakeUserService;
//import com.callforcode.agridist.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserService userService;
////    private FakeUserService userService;
//
//    @GetMapping("/users")
////    @RequestMapping(value = "/rest", method = RequestMethod.GET)
//    public ResponseEntity<List<User1>> getUsers() {
//        List<User1> users = this.userService.getAllUsers();
//        if(users.size() <= 0) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//        return ResponseEntity.of(Optional.of(users));
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<Optional<User1>> getUserById(@PathVariable("id") int id) {
//        Optional<User1> user = this.userService.getUserById(id);
//        if (user.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.of(Optional.of(user));
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<User1> addUser(@RequestBody User1 u) {
//        try{
//            User1 user = this.userService.addUser(u);
//            return ResponseEntity.status(HttpStatus.CREATED).body(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<User1> updateUser(@RequestBody User1 u, @PathVariable("id") int id) {
//        try {
//            this.userService.updateUser(u, id);
//            return ResponseEntity.ok().body(u);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
//        try {
//            this.userService.deleteUser(id);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } catch(Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}
//
