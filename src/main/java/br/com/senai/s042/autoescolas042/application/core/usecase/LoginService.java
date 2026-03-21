package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosAutenticacao;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosTokenJWT;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import br.com.senai.s042.autoescolas042.config.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final TokenService tokenService;
    private final AuthenticationManager manager;

    public LoginService(
        TokenService tokenService,
        AuthenticationManager manager){
    this.tokenService = tokenService;
    this.manager = manager;
    }

    public DadosTokenJWT logar (DadosAutenticacao dados){

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            dados.login(),
                            dados.senha()
                    );

            Authentication authentication = manager.authenticate(token);
            String tokenJWT = tokenService
                    .gerarToken((Usuario) authentication.getPrincipal());
            return new DadosTokenJWT(tokenJWT);
        }



}
