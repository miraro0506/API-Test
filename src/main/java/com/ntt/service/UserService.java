package com.ntt.service;

import com.ntt.dto.TelefonoResponse;
import com.ntt.dto.Usuario;
import com.ntt.dto.UsuarioResponse;
import com.ntt.repositorio.UserRepository;
import com.ntt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Value("${user.password.regex}")
    private String passwordRegex;

    public List<UsuarioResponse> listUsers() {
        return userRepository.findAll().stream().map(usuario -> {
            List<TelefonoResponse> telefonos = usuario.getTelefonos().stream().map(tel ->
                    new TelefonoResponse(tel.getNumero(), tel.getCodigoCiudad(), tel.getCodigoPais())
            ).toList();

            return new UsuarioResponse(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getCorreo(),
                    usuario.getContraseña(),
                    usuario.isActivo(),
                    telefonos
            );
        }).toList();
    }

    public Usuario createUser(Usuario user){

        Optional<Usuario> existente = userRepository.findAll().stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(user.getCorreo()))
                .findFirst();

        if (existente.isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        if (!user.getContraseña().matches(passwordRegex)) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres, una mayúscula y un número");
        }
        else{
            String token = JwtUtil.generarToken(user.getCorreo());
            user.setToken(token);
            user.setCreado(LocalDateTime.now());
            user.setUltimoLogin(LocalDateTime.now());
            user.setActivo(true);
            return this.userRepository.save(user);
        }
    }

    public Usuario actualizeUser(Usuario user){
        if(user.getId() == null) {
            throw new IllegalArgumentException("Debe agregar un id, para identificar al usuario");
        }else {
            Usuario usr = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            if (usr.getId().equals(user.getId())) {
                usr.setNombre(user.getNombre());
                usr.setCorreo(user.getCorreo());
                usr.setContraseña(user.getContraseña());
                usr.setActivo(user.isActivo());
                usr.getTelefonos().clear();
                usr.getTelefonos().addAll(user.getTelefonos());
                usr.setModificado(LocalDateTime.now());
                user.setUltimoLogin(LocalDateTime.now());
            }
            return this.userRepository.save(usr);
        }
    }

    public Usuario patchUser(Long id, Usuario usr) {
        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usr.getNombre() != null) {
            usuario.setNombre(usr.getNombre());
        }
        if (usr.getCorreo() != null) {
            usuario.setCorreo(usr.getCorreo());
        }
        if (usr.getContraseña() != null) {
            usuario.setContraseña(usr.getContraseña());
        }

        usuario.setModificado(LocalDateTime.now());
        return userRepository.save(usuario);
    }

    public boolean eliminateUser(Long id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}


