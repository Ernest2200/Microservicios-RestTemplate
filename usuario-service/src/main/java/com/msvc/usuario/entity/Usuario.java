package com.msvc.usuario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "id")
    private String usuarioId;
    @Column(name = "nombre", length = 20)
    private String nombre;
    @Column(name = "email")
    private String email;
    @Column(name = "informacion")
    private String informacion;
    @Transient
    private List<Calificacion> calificaciones = new ArrayList<>();


}
