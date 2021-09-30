package com.hector.cinturonnegro.repositories;

import com.hector.cinturonnegro.models.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends BaseRepository<Message>{
    List<Message> findAllByOrderByNumDenunciasDesc();
}
