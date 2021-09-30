package com.hector.cinturonnegro.services;

import com.hector.cinturonnegro.models.Notificacion;
import com.hector.cinturonnegro.repositories.BaseRepository;
import com.hector.cinturonnegro.repositories.NotificacionRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService extends BaseService<Notificacion>{
    private final NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        super(notificacionRepository);
        this.notificacionRepository = notificacionRepository;
    }



}
