package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.usuario.DadosAutenticacao;
import br.com.senai.s042.autoescolas042.domain.usuario.LoginService;
import br.com.senai.s042.autoescolas042.infra.security.DadosTokenJWT;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final LoginService service;

    public AutenticacaoController(LoginService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(
            @RequestBody @Valid DadosAutenticacao dados) {
        DadosTokenJWT dto = service.logar(dados);
        return ResponseEntity.ok(dto);
    }
}
