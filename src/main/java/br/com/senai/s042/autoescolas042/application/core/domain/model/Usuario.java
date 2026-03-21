package br.com.senai.s042.autoescolas042.application.core.domain.model;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosCadastroUsuarios;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Role perfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public Usuario(DadosCadastroUsuarios dadosUsuario) {
        this.login = dadosUsuario.login();
        this.senha = dadosUsuario.senha();
        this.ativo = true;
        this.perfil = dadosUsuario.perfil();
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {

        if(dadosAtualizacaoUsuario.login() != null && !dadosAtualizacaoUsuario.login().isBlank()){
            this.login = dadosAtualizacaoUsuario.login();
        }

        if(dadosAtualizacaoUsuario.senha() != null && !dadosAtualizacaoUsuario.senha().isBlank()){
            this.senha = dadosAtualizacaoUsuario.senha();
        }

        if(dadosAtualizacaoUsuario.perfil() !=null){
            this.perfil = dadosAtualizacaoUsuario.perfil();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
