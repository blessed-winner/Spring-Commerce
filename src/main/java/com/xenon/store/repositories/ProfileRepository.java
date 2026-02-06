package com.xenon.store.repositories;


import com.xenon.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}