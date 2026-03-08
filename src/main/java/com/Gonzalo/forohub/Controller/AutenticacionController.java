package com.Gonzalo.forohub.Controller;

import com.Gonzalo.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.Gonzalo.forohub.domain.usuario.Usuario;
import com.Gonzalo.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosTokenJWT> autenticarUsuario(
            @RequestBody @Valid DatosAutenticacionUsuario datos) {
        var authToken = new UsernamePasswordAuthenticationToken(
                datos.correoElectronico(), datos.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var tokenJWT = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

    public record DatosTokenJWT(String token) {}
}