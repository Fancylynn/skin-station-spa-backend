package com.fancylynn.skinstationspa.dao;

/**
 * Created by Lynn on 2018/1/24.
 */
import com.fancylynn.skinstationspa.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserDao extends CrudRepository<UserInfo, Long>{
    UserInfo findByUsername(String username);

    UserInfo findByEmail(String email);

//    UserInfo createUser(UserInfo user, Set<UserRole> userRoles);

    UserInfo save(UserInfo user);
}
