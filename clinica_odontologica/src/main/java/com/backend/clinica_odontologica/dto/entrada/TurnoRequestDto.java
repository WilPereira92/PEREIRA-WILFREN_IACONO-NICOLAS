package com.backend.clinica_odontologica.dto.entrada;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoRequestDto {
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha del turno")
    @JsonProperty("fecha_turno")
    private LocalDateTime fechaYHoraTurno;
    @NotNull(message = "El turno tiene que tener un paciente registrado")
    private Long paciente_id;

    @NotNull(message = "El turno tiene que tener un odontólogo registrado")
    private Long odontologo_id;

    public TurnoRequestDto() {
    }

    public TurnoRequestDto(LocalDateTime fechaYHoraTurno, Long paciente_id, Long odontologo_id) {
        this.fechaYHoraTurno = fechaYHoraTurno;
        this.paciente_id = paciente_id;
        this.odontologo_id = odontologo_id;
    }

    public LocalDateTime getFechaYHoraTurno() {
        return fechaYHoraTurno;
    }

    public void setFechaYHoraTurno(LocalDateTime fechaYHoraTurno) {
        this.fechaYHoraTurno = fechaYHoraTurno;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public Long getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Long odontologo_id) {
        this.odontologo_id = odontologo_id;
    }
}
