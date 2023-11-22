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
    private Long idPaciente;
    @JsonProperty("odontologo")
    @NotNull(message = "El turno tiene que tener un odontólogo registrado")
    private Long idOdontologo;

    public TurnoRequestDto() {
    }

    public TurnoRequestDto(LocalDateTime fechaYHoraTurno, Long idPaciente, Long idOdontologo) {
        this.fechaYHoraTurno = fechaYHoraTurno;
        this.idPaciente = idPaciente;
        this.idOdontologo = idOdontologo;
    }

    public LocalDateTime getFechaYHoraTurno() {
        return fechaYHoraTurno;
    }

    public void setFechaYHoraTurno(LocalDateTime fechaYHoraTurno) {
        this.fechaYHoraTurno = fechaYHoraTurno;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdOdontologo() {
        return idOdontologo;
    }

    public void setIdOdontologo(Long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }
}
