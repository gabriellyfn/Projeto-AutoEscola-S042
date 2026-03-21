package br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario;

import br.com.senai.s042.autoescolas042.application.core.domain.enums.Role;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String senha,
        Boolean ativo,
        Role perfil
) {

    public DadosDetalhamentoUsuario(Usuario usuario){

        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getAtivo(),
                usuario.getPerfil()
        );
    }
}

