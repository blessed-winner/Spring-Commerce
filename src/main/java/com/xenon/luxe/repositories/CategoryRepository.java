package com.xenon.luxe.repositories;

import com.xenon.luxe.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}