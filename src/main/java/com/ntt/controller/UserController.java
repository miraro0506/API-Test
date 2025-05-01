
package com.ntt.controller;

import com.ntt.dto.UsuarioResponse;
import com.ntt.service.UserService;
import com.ntt.dto.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService usuarioService){
        this.userService = usuarioService;
    }

    @GetMapping
    public ResponseEntity <List<UsuarioResponse>> getListUsers(){
        List<UsuarioResponse> usrs = userService.listUsers();
        return ResponseEntity.ok(usrs);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody @Validated Usuario user){
        Usuario usr = this.userService.createUser(user);
        return ResponseEntity.ok(usr);
    }

    @PutMapping
    public ResponseEntity<Usuario> updateUser(@RequestBody Usuario user){
        Usuario usr = this.userService.actualizeUser(user);
        return ResponseEntity.ok(usr);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> patchUser(@PathVariable Long id,@RequestBody Usuario usr) {
        Usuario updateUsr = userService.patchUser(id, usr);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Usuario modificado exitosamente");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProducto(@PathVariable Long id) {
        boolean eliminated = userService.eliminateUser(id);

        Map<String, String> response = new HashMap<>();
        if (eliminated) {
            response.put("mensaje", "Usuario eliminado exitosamente");
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
