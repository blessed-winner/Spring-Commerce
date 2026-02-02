package com.xenon.store.repositories;

import com.xenon.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

}
