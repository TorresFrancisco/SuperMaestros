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
@Table( name = "feedbacks" )
public class Feedback extends BaseModel{
    //PUNTUACION
    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
    //COMENTARIO
    @NotNull
    @NotBlank( message = "Debe tener un Comentario" )
    @Size( min = 5, max = 255, message = "Ingresa un comentario entre 5 y 255 caracteres" )
    private String comment;
    //FOTO RETROALIMENTACION
    private String photo_feedback;

    //RELACION CON USER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    //RELACION CON PUBLICATION
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication")
    private Publication publication;

    @OneToMany(mappedBy = "feedback", fetch = FetchType.LAZY)
    private List<Notificacion> notificaciones;

}
