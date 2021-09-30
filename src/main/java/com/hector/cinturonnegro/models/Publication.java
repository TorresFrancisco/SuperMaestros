package com.hector.cinturonnegro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "publications" )
public class Publication extends BaseModel{
    //TITULO
    @NotNull
    @NotBlank( message = "Debe tener un Título")
    @Size( min = 5, max = 45, message = "Ingresa un Título entre 5 a 25 caracteres" )
    private String title;
    //DESCRIPCION
    @NotNull
    @NotBlank( message = "Debe tener una descripción")
    @Size( min = 5, max = 255, message = "Ingresa una descripción entre 5 a 255 caracteres" )
    private String description;
    //PRECIO

    @Min(value = 1, message ="Debe ser mayor o igual a 1" )
    @Max(999999999)
    private int price;
    //FOTO PUBLICACION

    private String photo_publication;
    //TIPO DE PUBLICACION

    @Min(1)
    @Max(2)
    private int type_publication;

    //Estado Publicacion
    private boolean estado = true;

    //Estado SOS
    private boolean sos = false;

    ///////////////RELACIONES///////////////

    //RELACION CON USER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    //RELACION CON FEEDBACK
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Feedback> feedback;

    //RELACION CON CATEGORY
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;


    //RELACION CON MESSAGE
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Message> messages;

    //RELACION CON ADDRESS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Notificacion> notificaciones;


}
