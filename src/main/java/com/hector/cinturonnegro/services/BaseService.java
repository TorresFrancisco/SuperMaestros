package com.hector.cinturonnegro.services;

import com.hector.cinturonnegro.repositories.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseService<T> {
    BaseRepository<T> baseRepository;

    public BaseService(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<T> allData() {
        return baseRepository.findAll();
    }

    public T create(T entity) {
        return baseRepository.save(entity);
    }

    public T findById(Long id) {
        Optional<T> tOptional = baseRepository.findById(id);
        if(tOptional.isPresent()) {
            return tOptional.get();
        } else {
            return null;
        }
    }

    public T update(T entity) {
        return baseRepository.save(entity);
    }

    public void delete(Long id) {
        baseRepository.deleteById(id);
    }
}
