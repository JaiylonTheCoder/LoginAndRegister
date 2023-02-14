package com.jaiylonbabb.LoginAndRegister.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaiylonbabb.LoginAndRegister.Models.*;

public interface UserRepository extends JpaRepository <User, Long>{
    User findByUsername(String username);
    User findByFirstnameAndLastname(String firstname, String lastname);
}
