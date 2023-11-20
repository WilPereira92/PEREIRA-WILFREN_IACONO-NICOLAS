package com.backend.clinica_odontologica.dto.entrada.modificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoResquestUpdateDto {
    @NotNull(message = "Debe proveerse el id del odontólogo que se desea modificar")
    private Long id;
    @NotNull(message = "La matrícula del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse la matrícula del odontólogo")
    @Size(max = 50, message = "La matrícula debe tener hasta 50 caracteres")
    private String matricula;
    @NotNull(message = "El nombre del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontólogo")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String nombre;
    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontólogo")
    private String apellido;

    public OdontologoResquestUpdateDto() {
    }

    public OdontologoResquestUpdateDto(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
