package com.msvc.calificacion.services;

import com.msvc.calificacion.entity.Calificacion;
import com.msvc.calificacion.repository.CalificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionService{

    private final CalificacionRepository calificacionRepository;

    @Override
    public Calificacion create(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion> getCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Override
    public List<Calificacion> getCalificacionesByUsuarioId(String usuarioId) {
        return calificacionRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Calificacion> getCalificacionByHotelId(String hotelId) {
        return calificacionRepository.findByHotelId(hotelId);
    }
}
