package com.jaiylonbabb.LoginAndRegister.Pagination;

import com.jaiylonbabb.LoginAndRegister.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
//    Iterable<T> findAll(Sort sort);
//
//    Page<T> findAll(Pageable pageable);
//    List<User> findByNameContaining(String name, Sort sort);

}
