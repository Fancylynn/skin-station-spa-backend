package com.fancylynn.skinstationspa.dao;

/**
 * Created by Lynn on 2018/1/24.
 */
import com.fancylynn.skinstationspa.model.User;
import com.fancylynn.skinstationspa.model.security.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserDao extends CrudRepository<User, Long>{
    User findByUsername(String username);

    User findByEmail(String email);

//    User createUser(User user, Set<UserRole> userRoles);

    User save(User user);
}
