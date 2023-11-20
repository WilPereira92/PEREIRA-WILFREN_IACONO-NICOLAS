package com.backend.clinica_odontologica.dto.salida;

import java.time.LocalDateTime;

public class TurnoResponseDto {
    private Long id;
    private LocalDateTime fechaYHoraTurno;
    private PacienteResponseDto pacienteResponseDto;
    private OdontologoResponseDto odontologoResponseDto;

    public TurnoResponseDto() {
    }

    public TurnoResponseDto(Long id, LocalDateTime fechaYHoraTurno, PacienteResponseDto pacienteResponseDto, OdontologoResponseDto odontologoResponseDto) {
        this.id = id;
        this.fechaYHoraTurno = fechaYHoraTurno;
        this.pacienteResponseDto = pacienteResponseDto;
        this.odontologoResponseDto = odontologoResponseDto;
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

    public PacienteResponseDto getPacienteResponseDto() {
        return pacienteResponseDto;
    }

    public void setPacienteResponseDto(PacienteResponseDto pacienteResponseDto) {
        this.pacienteResponseDto = pacienteResponseDto;
    }

    public OdontologoResponseDto getOdontologoResponseDto() {
        return odontologoResponseDto;
    }

    public void setOdontologoResponseDto(OdontologoResponseDto odontologoResponseDto) {
        this.odontologoResponseDto = odontologoResponseDto;
    }
}
