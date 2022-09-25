//package com.callforcode.agridist.dao;
//
//import com.callforcode.agridist.entities.User1;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UserRepository extends CrudRepository<User1, Integer> {
//    public List<User1> findByName(String Name);
//
//    @Query("Select u from User1 u")
//    public List<User1> getAllUser();
////
//    @Query("select u from User1 u where u.name =:n")
//    public List<User1> getUserByName(@Param("n") String name);
//
//    @Query(value = "Select * from User1 u", nativeQuery = true)
//    public List<User1> getUsers();
//
//}
