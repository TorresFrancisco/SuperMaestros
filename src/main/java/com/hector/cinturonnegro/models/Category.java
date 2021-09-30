package com.hector.cinturonnegro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "categories" )
public class Category extends BaseModel{
    //NOMBRE DE LA CATEGORIA
    @NotNull
    @NotBlank( message = "La Categoría debe tener un nombre" )
    @Size( min = 5, max = 25, message = "Ingresa un nombre para el campo categoría entre 5 a 25 caracteres" )
    private String name;

    //RELACION CON PUBLICACION
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Publication> publications;





}


