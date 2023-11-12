package com.backend.clinica_odontologica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class PacienteRequestDto {
    @NotNull(message = "El nombre del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String nombre;

    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    private String apellido;

    @NotNull(message = "El dni del paciente no puede ser nulo")
    @Digits(integer = 8, fraction = 0, message = "El dni debe tener como máximo 8 dígitos")
    private Integer dni;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    //@JsonProperty("fecha_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @NotNull(message = "El domicilio del paciente no puede ser nulo")
    @JsonProperty("domicilio")
    @Valid
    private DomicilioRequestDto domicilioRequestDto;

    public PacienteRequestDto() {
    }

    public PacienteRequestDto(String nombre, String apellido, Integer dni, LocalDate fechaIngreso, DomicilioRequestDto domicilioRequestDto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioRequestDto = domicilioRequestDto;
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

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioRequestDto getDomicilioRequestDto() {
        return domicilioRequestDto;
    }

    public void setDomicilioRequestDto(DomicilioRequestDto domicilioRequestDto) {
        this.domicilioRequestDto = domicilioRequestDto;
    }
}
