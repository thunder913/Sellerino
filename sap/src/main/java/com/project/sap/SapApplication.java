package com.project.sap;


import com.project.sap.api.DBController;
import com.project.sap.models.RAM;
import com.project.sap.repositories.RAMRepository;
import com.project.sap.repositories.UserRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.PersistenceContext;
import java.util.List;

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
