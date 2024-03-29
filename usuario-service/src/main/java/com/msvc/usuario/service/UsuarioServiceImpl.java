package com.msvc.usuario.service;

import com.msvc.usuario.entity.Calificacion;
import com.msvc.usuario.entity.Hotel;
import com.msvc.usuario.entity.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String ramdomUsuarioId = UUID.randomUUID().toString();
        usuario.setUsuarioId(ramdomUsuarioId);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(String usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + usuarioId));

        Calificacion[] calificacionDelUsuario = restTemplate.getForObject("http://CALIFICACION-SERVICE/calificaciones/usuarios/" + usuario.getUsuarioId(), Calificacion[].class);
        List<Calificacion> calificaciones = Arrays.stream(calificacionDelUsuario).collect(Collectors.toList());

        List<Calificacion> listaCalificaciones = calificaciones.stream().map(calificacion -> {
            System.out.println(calificacion.getHotelId());
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hoteles/" + calificacion.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("Respuesta con codigo de estado: {}", forEntity.getStatusCode());
            calificacion.setHotel(hotel);
            return calificacion;
        }).collect(Collectors.toList());

        usuario.setCalificaciones(listaCalificaciones);

        return usuario;


    }
}
