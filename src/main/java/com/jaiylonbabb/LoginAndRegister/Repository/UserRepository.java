package com.jaiylonbabb.LoginAndRegister.Repository;

import com.jaiylonbabb.LoginAndRegister.Pagination.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jaiylonbabb.LoginAndRegister.Models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository <User, Long>, PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
    User findByFirstnameAndLastname(String firstname, String lastname);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
    void deleteById(Long id);
//    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
