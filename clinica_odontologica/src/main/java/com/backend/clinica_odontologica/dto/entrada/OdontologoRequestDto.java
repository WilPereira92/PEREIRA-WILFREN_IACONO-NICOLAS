package com.backend.clinica_odontologica.dto.entrada;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OdontologoRequestDto {
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

    public OdontologoRequestDto() {
    }

    public OdontologoRequestDto(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
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
