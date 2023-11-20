package com.backend.clinica_odontologica.dto.entrada;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoRequestDto {
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha del turno")
    @JsonProperty("fecha_turno")
    private LocalDateTime fechaYHoraTurno;
    @JsonProperty("paciente")
    @NotNull(message = "El turno tiene que tener un paciente registrado")
    @Valid
    private PacienteRequestDto pacienteRequestDto;
    @JsonProperty("odontologo")
    @NotNull(message = "El turno tiene que tener un odontólogo registrado")
    @Valid
    private OdontologoRequestDto odontologoRequestDto;

    public TurnoRequestDto() {
    }

    public TurnoRequestDto(LocalDateTime fechaYHoraTurno, PacienteRequestDto pacienteRequestDto, OdontologoRequestDto odontologoRequestDto) {
        this.fechaYHoraTurno = fechaYHoraTurno;
        this.pacienteRequestDto = pacienteRequestDto;
        this.odontologoRequestDto = odontologoRequestDto;
    }

    public LocalDateTime getFechaYHoraTurno() {
        return fechaYHoraTurno;
    }

    public void setFechaYHoraTurno(LocalDateTime fechaYHoraTurno) {
        this.fechaYHoraTurno = fechaYHoraTurno;
    }

    public PacienteRequestDto getPacienteRequestDto() {
        return pacienteRequestDto;
    }

    public void setPacienteRequestDto(PacienteRequestDto pacienteRequestDto) {
        this.pacienteRequestDto = pacienteRequestDto;
    }

    public OdontologoRequestDto getOdontologoRequestDto() {
        return odontologoRequestDto;
    }

    public void setOdontologoRequestDto(OdontologoRequestDto odontologoRequestDto) {
        this.odontologoRequestDto = odontologoRequestDto;
    }
}
