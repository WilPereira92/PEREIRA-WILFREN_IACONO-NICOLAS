package com.backend.clinica_odontologica.dto.entrada.modificacion;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoRequestUpdateDto {
    @NotNull(message = "Debe proveerse el id del turno que desea modificar")
    private Long id;
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha del turno")
    private LocalDateTime fechaYHoraTurno;
    @NotNull(message = "El turno tiene que tener un paciente registrado")
    @Valid
    private PacienteResquestUpdateDto pacienteResquestUpdateDto;
    @NotNull(message = "El turno tiene que tener un odontólogo registrado")
    @Valid
    private OdontologoResquestUpdateDto odontologoResquestUpdateDto;

    public TurnoRequestUpdateDto() {
    }

    public TurnoRequestUpdateDto(Long id, LocalDateTime fechaYHoraTurno, PacienteResquestUpdateDto pacienteResquestUpdateDto, OdontologoResquestUpdateDto odontologoResquestUpdateDto) {
        this.id = id;
        this.fechaYHoraTurno = fechaYHoraTurno;
        this.pacienteResquestUpdateDto = pacienteResquestUpdateDto;
        this.odontologoResquestUpdateDto = odontologoResquestUpdateDto;
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

    public PacienteResquestUpdateDto getPacienteResquestUpdateDto() {
        return pacienteResquestUpdateDto;
    }

    public void setPacienteResquestUpdateDto(PacienteResquestUpdateDto pacienteResquestUpdateDto) {
        this.pacienteResquestUpdateDto = pacienteResquestUpdateDto;
    }

    public OdontologoResquestUpdateDto getOdontologoResquestUpdateDto() {
        return odontologoResquestUpdateDto;
    }

    public void setOdontologoResquestUpdateDto(OdontologoResquestUpdateDto odontologoResquestUpdateDto) {
        this.odontologoResquestUpdateDto = odontologoResquestUpdateDto;
    }
}
