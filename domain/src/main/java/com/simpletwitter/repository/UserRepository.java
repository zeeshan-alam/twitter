package com.simpletwitter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.simpletwitter.domain.*;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
