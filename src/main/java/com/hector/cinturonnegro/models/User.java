package com.hector.cinturonnegro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "users", uniqueConstraints = @UniqueConstraint( columnNames = "email" ) )
public class User extends BaseModel{
    //NOMBRE
    @NotNull
    @NotBlank( message = "Debe tener un nombre")
    @Size( min = 2, max = 20, message = "Ingresa un Nombre entre 2 a 20 caracteres" )
    private String firstName;
    //APELLIDO
    @NotNull
    @NotBlank( message = "Debe tener un Apellido")
    @Size( min = 2, max = 20, message = "Ingresa un Apellido entre 2 a 20 caracteres" )
    private String lastName;
    //FOTO
    private String photo;
    //TELEFONO
    @NotNull
    @NotBlank( message = "Debe tener un número de Celular" )
    @Size( min = 12, message = "El número telefónico debe tener 12 caracteres")
    private String phone;
    //EMAIL
    @NotNull
    @Column( unique = true )
    @Email( message = "El Email debe ser válido" )
    private String email;
    //CONTRASEÑA
    @NotNull
    @Size( min = 8, message = "La Contraseña debe ser mayor a 8 caracteres" )
    private String password;
    //CONFIRMAR CONTRASEÑA
    @Transient
    private String passwordConfirmation;
//    //REGION
//    @NotNull
//    private String region;
//    //COMUNA
//    @NotNull
//    private String comuna;
    //ROL
    @NotNull
    @Min( 1 )
    ////////ROLES/////////////
    private int rol;
    ///////////BANEO////////////
    private boolean available = true;

    ///////////RELACIONES/////////////

    //RELACION CON PUBLICATION
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Publication> publications;

    //RELACION CON FEEDBACK
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;

    //RELACION CON MESSAGES
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Message> messages;

    @OneToMany(mappedBy = "receptor", fetch = FetchType.LAZY)
    private List<Message> messagesReceptor;


//    //////////////////RELACION CON DIRECCIONES/////////////////
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Address")
    private Address address;

    ///////////RELACION CON NOTIFICACIONES////////////////
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Notificacion> notificacions;


}
