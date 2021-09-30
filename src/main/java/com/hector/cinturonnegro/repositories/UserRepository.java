package com.hector.cinturonnegro.repositories;

import com.hector.cinturonnegro.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User>{
    User findUserByEmail(String email);

    boolean existsByEmail(String email);
}
