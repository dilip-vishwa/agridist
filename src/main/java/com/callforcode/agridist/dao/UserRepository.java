package com.callforcode.agridist.dao;

import com.callforcode.agridist.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    public List<User> findByName(String Name);

    @Query("Select u from User u")
    public List<User> getAllUser();
//
    @Query("select u from User u where u.name =:n")
    public List<User> getUserByName(@Param("n") String name);

    @Query(value = "Select * from User u", nativeQuery = true)
    public List<User> getUsers();

}
