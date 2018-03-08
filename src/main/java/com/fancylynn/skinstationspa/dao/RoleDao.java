package com.fancylynn.skinstationspa.dao;

import com.fancylynn.skinstationspa.model.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lynn on 2018/3/6.
 */
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByUsername(String username);
}
