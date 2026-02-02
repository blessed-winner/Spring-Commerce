package com.xenon.store.repositories;

import com.xenon.store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
