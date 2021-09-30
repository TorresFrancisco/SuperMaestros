package com.hector.cinturonnegro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "addresses" )
public class Address extends BaseModel{
    @NotNull
    @NotBlank(message = "Debe tener una Dirección")
    @Size(min = 2, max = 255)
    private String nameCalle;


    ///////////////RELACION CON USUARIOS/////////////////
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User userAddress;



    ///////////////RELACION CON COMUNAS///////////////////
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comuna")
    private Comuna comuna;

    public Address(String calle, Comuna comuna){
        this.nameCalle = calle;
        this.comuna = comuna;
    }

    /////////////RELACION CON PUBLICACION///////////////////
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private List<Publication> publications;

}
