package com.hector.cinturonnegro.repositories;

import com.hector.cinturonnegro.models.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends BaseRepository<Category>{

    //Buscar categorias por nombre
    List<Category> findByName(String name);

}
