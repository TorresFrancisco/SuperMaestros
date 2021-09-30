package com.hector.cinturonnegro.repositories;

import com.hector.cinturonnegro.models.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends BaseRepository<Region>{

    //Buscar region por nombre de region
    Region findByNameRegionContaining(String nameRegion);

    List<Region> findByNameRegion(String listRegion);


}
