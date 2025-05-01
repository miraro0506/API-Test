package com.ntt.repositorio;

import com.ntt.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <Usuario, Long>{

}
