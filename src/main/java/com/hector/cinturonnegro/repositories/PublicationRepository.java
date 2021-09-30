package com.hector.cinturonnegro.repositories;

import com.hector.cinturonnegro.models.Category;
import com.hector.cinturonnegro.models.Publication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends BaseRepository<Publication>{

    // Buscar publicaciones por titulo
    List<Publication> findByTitleContaining(String title);

    List<Publication> findByDescriptionContaining(String description);

    @Query(value = "SELECT * FROM publications p JOIN addresses a ON a.id=p.address JOIN comunas c ON a.comuna = c.id JOIN regiones r ON c.region = r.id WHERE r.id LIKE %?1%", nativeQuery = true)
    List<Publication> findByRegionContainingAndEstadoIsTrue(Long query);

    @Query(value = "SELECT * FROM publications p JOIN addresses a ON a.id=p.address JOIN comunas c ON a.comuna = c.id JOIN regiones r ON c.region = r.id WHERE r.name_region LIKE %?1%", nativeQuery = true)
    List<Publication> findByRegionContainingAndEstadoIsTrue(String query);

    @Query(value = "SELECT * FROM publications p JOIN addresses a ON a.id=p.address JOIN comunas c ON a.comuna = c.id WHERE c.id LIKE %?1%", nativeQuery = true)
    List<Publication> findByComunaContainingAndEstadoIsTrue(Long query);

    @Query(value = "SELECT * FROM publications p JOIN addresses a ON a.id=p.address JOIN comunas c ON a.comuna = c.id WHERE c.name_comuna LIKE %?1%", nativeQuery = true)
    List<Publication> findByComunaContainingAndEstadoIsTrue(String query);

    List<Publication> findByEstadoIsTrue();

    List<Publication> findByCategoryAndEstadoIsTrue(Category category);


}
