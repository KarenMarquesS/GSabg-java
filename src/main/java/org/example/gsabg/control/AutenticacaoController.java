package org.example.gsabg.control;

import org.example.gsabg.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String gerarTokenValido(@RequestParam String username, @RequestParam String password) {
        try {

            var auth = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(auth);
            return jwtUtil.gerartoken(username);


        } catch (Exception e) {
            return "Usuário ou senha inválidos";
        }

    }

}
