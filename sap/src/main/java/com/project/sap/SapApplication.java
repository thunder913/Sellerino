package com.project.sap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SapApplication {



	public static void main(String[] args) throws Exception {

		//DBController db = new DBController();
//
		//List<User> users = db.getUsers();
//
		//for (User user: users) {
		//	System.out.println(user.toString());
		//}

		SpringApplication.run(SapApplication.class, args);
	}

}
