package br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;

public record DadosListagemUsuarios(
        Long id,
        String login,
        String senha) {

    public DadosListagemUsuarios(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }
}
