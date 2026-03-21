package br.com.senai.s042.autoescolas042.domain.usuario;

import br.com.senai.s042.autoescolas042.infra.security.DadosTokenJWT;
import br.com.senai.s042.autoescolas042.infra.security.TokenService;
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
