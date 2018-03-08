package com.fancylynn.skinstationspa;

/**
 * Created by Lynn on 2018/1/24.
 */
import com.fancylynn.skinstationspa.model.User;
import com.fancylynn.skinstationspa.model.security.Role;
import com.fancylynn.skinstationspa.model.security.UserRole;
import com.fancylynn.skinstationspa.service.UserService;
import com.fancylynn.skinstationspa.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application extends SpringBootServletInitializer  {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}


//@SpringBootApplication
//public class Application implements CommandLineRunner {
//    @Autowired
//    private UserService userService;
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        User user1 = new User();
//        user1.setFirstName("John");
//        user1.setLastName("Adams");
//        user1.setUsername("j");
//        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
//        user1.setEmail("JAdams@gmail.com");
//        Set<UserRole> userRoles = new HashSet<>();
//        Role role1 = new Role();
//        role1.setRoleId(1);
//        role1.setUsername("ROLE_USER");
//        userRoles.add(new UserRole(user1, role1));
//        userService.createUser(user1, userRoles);
//
//    }
//}