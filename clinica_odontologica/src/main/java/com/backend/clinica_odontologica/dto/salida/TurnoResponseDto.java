package com.backend.clinica_odontologica.dto.salida;

import java.time.LocalDateTime;

public class TurnoResponseDto {
    private Long id;
    private LocalDateTime fechaYHoraTurno;
    private Long odontologo_id;
    private Long paciente_id;

    public TurnoResponseDto() {
    }

    public TurnoResponseDto(Long id, LocalDateTime fechaYHoraTurno, Long odontologo_id, Long paciente_id) {
        this.id = id;
        this.fechaYHoraTurno = fechaYHoraTurno;
        this.odontologo_id = odontologo_id;
        this.paciente_id = paciente_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHoraTurno() {
        return fechaYHoraTurno;
    }

    public void setFechaYHoraTurno(LocalDateTime fechaYHoraTurno) {
        this.fechaYHoraTurno = fechaYHoraTurno;
    }

    public Long getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Long odontologo_id) {
        this.odontologo_id = odontologo_id;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }
}
