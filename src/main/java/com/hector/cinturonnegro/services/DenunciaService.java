package com.hector.cinturonnegro.services;

import com.hector.cinturonnegro.models.Denuncia;
import com.hector.cinturonnegro.repositories.BaseRepository;
import com.hector.cinturonnegro.repositories.DenunciaRepository;
import org.springframework.stereotype.Service;

@Service
public class DenunciaService extends BaseService<Denuncia>{
    private final DenunciaRepository denunciaRepository;

    public DenunciaService(DenunciaRepository denunciaRepository) {
        super(denunciaRepository);
        this.denunciaRepository = denunciaRepository;
    }
}
