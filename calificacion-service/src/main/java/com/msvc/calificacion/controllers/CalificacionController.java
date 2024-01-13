package com.msvc.calificacion.controllers;

import com.msvc.calificacion.entity.Calificacion;
import com.msvc.calificacion.services.CalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calificaciones")
public class CalificacionController {
    private final CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<Calificacion> guardarCalificacion(@RequestBody Calificacion calificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionService.create(calificacion));
    }

    @GetMapping
    public ResponseEntity<List<Calificacion>> listarCalificaciones() {
        return ResponseEntity.status(HttpStatus.OK).body(calificacionService.getCalificaciones());
    }


    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Calificacion>> listarCalificacionesPorUsuarioId(@PathVariable String usuarioId){
        return ResponseEntity.status(HttpStatus.OK).body(calificacionService.getCalificacionesByUsuarioId(usuarioId));
    }

    @GetMapping("/hoteles/{hotelId}")
    public ResponseEntity<List<Calificacion>> listarCalificacionesPorHotelId(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(calificacionService.getCalificacionByHotelId(hotelId));
    }
}