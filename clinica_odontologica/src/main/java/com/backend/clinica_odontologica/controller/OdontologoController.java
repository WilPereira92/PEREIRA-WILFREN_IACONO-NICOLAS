package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.OdontologoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.OdontologoResquestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoResponseDto;
import com.backend.clinica_odontologica.service.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoResponseDto> registrarOdontologo(@RequestBody @Valid OdontologoRequestDto odontologo) {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OdontologoResponseDto>> listarOdontologos() {
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<OdontologoResponseDto> buscarPorId(@RequestParam Long id) {
        return new ResponseEntity<>(odontologoService.buscarPorId(id), HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public OdontologoResponseDto actualizarOdontologo(@RequestBody OdontologoResquestUpdateDto odontologo) {
        return odontologoService.actualizarOdontologo(odontologo);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOdontologo(@RequestParam Long id) {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontólogo eliminado correctamente", HttpStatus.OK);
    }

}
