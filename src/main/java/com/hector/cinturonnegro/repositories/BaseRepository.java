package com.hector.cinturonnegro.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T> extends PagingAndSortingRepository<T, Long> {
    List<T> findAll();
}