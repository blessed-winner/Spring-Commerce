package com.xenon.luxe.repositories;

import com.xenon.luxe.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}