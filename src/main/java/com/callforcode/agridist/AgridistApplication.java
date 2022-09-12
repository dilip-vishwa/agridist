package com.callforcode.agridist;

import com.callforcode.agridist.dao.UserRepository;
import com.callforcode.agridist.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootApplication
public class AgridistApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AgridistApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
//		User user = new User();
//		user.setName("XYZ");
//		user.setAge(23);
//		User user1 = userRepository.save(user);
//		System.out.println(user1);

//		List<User> resultList = List.of(user);
//		Iterable<User> userList = userRepository.saveAll(resultList);
//		userList.forEach(user2 -> {
//			System.out.println(user2);
//		});


//		Optional<User> user4 = userRepository.findById(2);
//		User user5 = user4.get();
//		user5.setName("Ashish");
//		userRepository.save(user5);
//		System.out.println(user5);

//		Iterable<User> itrUser = userRepository.findAll();
//		Iterator<User> iterator = itrUser.iterator();
//		while(iterator.hasNext()) {
//			User user6 = iterator.next();
//			System.out.println(user6);
//		}

//		itrUser.forEach(new Consumer<User>() {
//			@Override
//			public void accept(User user) {
//				System.out.println(user);
//			}
//		});

// 		itrUser.forEach(user7->System.out.println(user7));

//		https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
//		List<User> userList = userRepository.findByName("XYZ");
//		userList.forEach(user8->System.out.println(user8));

//		List<User> userList1 = userRepository.getAllUser();
//		userList1.forEach(user9->System.out.println(user9));
//
//		List<User> userList2 = userRepository.getUserByName("Ashish");
//		userList2.forEach(user9->System.out.println(user9));
	}

}
