package com.ntt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "El campo nombre no debe ir vacío")
    private String nombre;
    @Email(message = "El correo tiene un formato inválido")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String correo;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "contrasenia")
    private String contraseña;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    private LocalDateTime ultimoLogin;
    @Column(name = "token")
    private String token;
    private boolean activo = true;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Telefono> telefonos = new ArrayList<>();


}
