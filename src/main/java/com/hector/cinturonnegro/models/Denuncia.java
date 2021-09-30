package com.hector.cinturonnegro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "denuncias")
public class Denuncia extends BaseModel{
    @Min(1)
    private int tipoDenuncia;

    private String detalles;

    //RELACION CON MENSAJES
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message")
    private Message message;
}
