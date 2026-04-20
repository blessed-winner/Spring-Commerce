package com.xenon.luxe.repositories;


import com.xenon.luxe.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}