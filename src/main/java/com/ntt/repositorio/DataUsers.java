package com.ntt.repositorio;

import com.ntt.dto.Telefono;
import com.ntt.dto.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataUsers {

    @Bean
    CommandLineRunner init(UserRepository repo){
        return args -> {
            Usuario usr = new Usuario();
            usr.setNombre("Daniel Rodriguez");
            usr.setCorreo("danRodriguez@gmail.com");
            usr.setContraseña("ntt2025");

            Telefono phone = new Telefono();
            phone.setNumero("123456789");
            phone.setCodigoCiudad("1");
            phone.setCodigoPais("56");

            usr.getTelefonos().add(phone);
            repo.save(usr);
        };
    }
}
